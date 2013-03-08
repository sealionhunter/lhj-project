package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Admission;
import model.Apply;
import model.Depart;
import model.Exam;
import model.Office;
import model.Room;
import model.RoomOffice;
import model.Seat;
import model.User;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import command.RoomEditCommand;
import command.SignUpPersonSearchCommand;
import command.UserSeatResetCommand;

import dao.AdmissionDao;
import dao.ApplyDao;
import dao.DepartDao;
import dao.ExamDao;
import dao.OfficeDao;
import dao.RoomDao;
import dao.UserDao;

public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao;
    private UserDao userDao;
    private DepartDao departDao;
    private OfficeDao officeDao;
    private ApplyDao applyDao;
    private ExamDao examDao;
    private AdmissionDao admissionDao;
    private boolean resetAdmissionAfterPrint;
    private int seatsPerRoom = 30;

    @SuppressWarnings("rawtypes")
    @Override
    public Map initEdit(RoomEditCommand cmd, String id, Errors errors)
            throws Exception {
        cmd.setOffices(officeDao.list());
        cmd.setDeparts(departDao.list());
        if (id != null && id.length() > 0) {
            Room room = roomDao.getRoom(Integer.valueOf(id));
            if (room != null) {
                cmd.setId(room.getId());
                cmd.setCode(room.getCode());
                cmd.setName(room.getName());
                cmd.setPosition(room.getPosition());
                cmd.setSeats(String.valueOf(room.getSeats()));
            }
        }
        return null;
    }

    @Override
    public void editOk(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception {
        cmd.setOffices(officeDao.list());
        cmd.setDeparts(departDao.list());
        if (cmd.getId() == null && roomDao.checkRoom(cmd.getCode())) {
            errors.rejectValue("code", "code", "指定编号的考场已存在，请重新指定");
            return;
        }
        Room room;
        int seats = Integer.parseInt(cmd.getSeats());
        if (cmd.getId() != null) {
            room = roomDao.getRoom(cmd.getId());
            if (seats < room.getRemainSeats()) {
                errors.rejectValue("seats", "seats", "座位数小于已分配的座位数");
                return;
            } else {
                room.setRemainSeats(seats - room.getSeats() + room.getRemainSeats());
            }
        } else {
            room = new Room();
            room.setId(Integer.parseInt(cmd.getCode()));
            room.setRemainSeats(seats);
        }
        room.setCode(cmd.getCode());
        room.setName(cmd.getName());
        room.setPosition(cmd.getPosition());
        room.setSeats(seats);
        roomDao.updateRoom(room);
    }

    @Transactional
    @Override
    public void generateRoom(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception {
        List<Office> offices = roomDao.listOfficeByCond(cmd);
        if (roomDao.listRoom().size() > 0) {
            throw new Exception("已经存在考场，无法自动生成，请使用考场添加功能，或者删除所有考场后再重试!");
        }
        int oCount = offices.size();
        List<Room> rooms = new ArrayList<Room>();
        int rid = 1;
        int remainO = 0;
        // rooms for single office
        for (int i = 0; i < oCount; i++) {
            Office o = offices.get(i);
            while (o.remain() > seatsPerRoom) {
                Room r = newRoom(rid++, seatsPerRoom);
                rooms.add(r);
                addRoomOffice(o, r, seatsPerRoom);
            }
            remainO += o.remain();
        }
        // rooms for share offices
        int remainR = 0;
        while (remainR < remainO) {
            rooms.add(newRoom(rid++, seatsPerRoom));
            remainR += seatsPerRoom;
        }
        // sort offices by remain
        Collections.sort(offices, new Comparator<Office>() {
            public int compare(Office o1, Office o2) {
                return o2.remain() - o1.remain();
            }
        });
        // calculate the share rooms
        while (remainO > 0) {
            for (Office o : offices) {
                if (o.remain() <= 0) {
                    continue;
                }
                Room rm = null;
                // find room that it's remain is greater the the o's remain
                for (Room r : rooms) {
                    if (r.remain() <= 0) {
                        continue;
                    }
                    if (o.remain() <= r.remain()) {
                        if (rm == null) {
                            rm = r;
                        } else if (rm.remain() < r.remain()) {
                            rm = r;
                        }
                    }
                }
                // there is no room that it's remain is greater then the o's
                // remain
                // then find room that has the largest remain
                if (rm == null) {
                    for (Room r : rooms) {
                        if (r.remain() <= 0) {
                            continue;
                        }
                        if (rm == null) {
                            rm = r;
                        } else if (rm.remain() < r.remain()) {
                            rm = r;
                        }
                    }
                    remainO -= rm.remain();
                    addRoomOffice(o, rm, rm.remain());
                } else {
                    remainO -= o.remain();
                    addRoomOffice(o, rm, o.remain());
                }

            }
        }
        for (Room r : rooms) {
            roomDao.addRoom(r);
            roomDao.addRoomOffice(r.getRoomOffices());
        }
    }

    private Room newRoom(int rid, int seats) {
        Room r = new Room();
        r.setId(rid);
        r.setCode(String.format("%02d", rid));
        r.setSeats(seatsPerRoom);
        r.setRemainSeats(seatsPerRoom);
        r.setName("合肥工业大学附属中学");
        r.setPosition("合肥市宁国南路103号");
        return r;
    }

    private RoomOffice addRoomOffice(Office o, Room r, int assignSeats) {
        RoomOffice ro = new RoomOffice();
        ro.setOfficeId(o.getId());
        ro.setRoomId(r.getId());
        ro.setAssignSeats(assignSeats);
        o.add(ro);
        r.add(ro);
        return ro;
    }

    @Transactional
    @Override
    public void generateAdmission(HttpServletRequest request,
            RoomEditCommand cmd, BindException errors) throws Exception {
        Exam exam = examDao.list().get(0);

        // check condition first
        checkCondition(exam, cmd);
        // yyMMddAABB
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String prefix = sdf.format(exam.getExamDate());

        // get applies and rooms
        SignUpPersonSearchCommand condition = new SignUpPersonSearchCommand();
        condition.setState(2);
        condition.setDeptId(cmd.getDepartId());
        condition.setPostId(cmd.getOfficeId());
        
        List<Apply> applies = applyDao.list(condition);
        List<Integer> uids = new ArrayList<Integer>();
        for (Apply apply : applies) {
            uids.add(apply.getUser().getId());
        }
        roomDao.removeSeatByUids(uids);
        admissionDao.deleteByUids(uids);
        
        List<RoomOffice> rooms = roomDao.listRoomOfficeByOid(-1);

        // admissions and seats for insert
        List<Admission> admissions = new ArrayList<Admission>(applies.size());
        List<Seat> seats = new ArrayList<Seat>(applies.size());

        // users for deleting admission and seats;
        Map<Integer, Integer> seqs = new HashMap<Integer, Integer>();
        for (Apply apply : applies) {
            RoomOffice ro = null;
            for (RoomOffice r : rooms) {
                if (r.getOfficeId() == apply.getId().getOfficeid()
                        && (r.getAssignedSeat() < r.getAssignSeats())) {
                    ro = r;
                    ro.incSeats();
                    break;
                }
            }
            if (ro == null) {
                continue;
            }

            Integer seq = seqs.get(ro.getRoomId());
            if (seq == null) {
                seq = 1;
                seqs.put(ro.getRoomId(), seq);
            } else {
                seqs.put(ro.getRoomId(), ++seq);
            }

            Integer uid = apply.getUser().getId();

            Seat seat = new Seat();
            seat.setCode(String.format("%02d", seq));
            seat.setRoomId(ro.getRoomId());
            seat.setUserId(uid);
            seats.add(seat);

            admissions.add(new Admission(uid, prefix + ro.getRoom().getCode()
                    + seat.getCode()));
        }
        roomDao.addSeat(seats);
        admissionDao.add(admissions);
    }

    private void checkCondition(Exam exam, RoomEditCommand condition)
            throws Exception {
        Date date = exam.getExamDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(date);
        String timeStr = exam.getExamTime();
        String[] timeArray = timeStr.split("-");

        String from = dateStr + " " + timeArray[0];
        String to = dateStr + " " + timeArray[1];

        sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
        Date fromTime = sdf.parse(from);
        Date toTime = sdf.parse(to);
        Date now = new Date();

        if (now.before(exam.getApplyBeginDate())) {
            throw new Exception("报名还未开始，不能分配座位！");
        }
        if (now.before(exam.getApplyDeadDate())) {
            throw new Exception("报名还未结束，不能分配座位！");
        }
        if (now.after(toTime)) {
            throw new Exception("考试已经结束，不能分配座位！");
        }
        if (now.after(fromTime)) {
            throw new Exception("考试已经开始，不能分配座位！");
        }
        // 存在未审核人员
        if (applyDao.hasUnVerified()) {
            throw new Exception("还有考生审核未完成，不能分配座位。请先审核完全部考生！");
        }
        if (!roomDao.checkSeats(condition)) {
            throw new Exception("考场座位数不够容纳全部考生，请先确认考场座位！");
        }
        if (!resetAdmissionAfterPrint && !roomDao.checkAdmission(condition)) {
            throw new Exception("已有考生打印了准考证号，无法重新分配座位");
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map initList(RoomEditCommand cmd) throws Exception {

        Map model = new HashMap();
        List<Depart> deptList = departDao.list();

        Depart dept = new Depart();
        dept.setId(-1);
        dept.setName("");
        deptList.add(0, dept);
        model.put("departs", deptList);

        List<Office> officeList = officeDao.list();
        Office office = new Office();
        office.setId(-1);
        office.setName("");
        office.setCode("");
        officeList.add(0, office);
        model.put("offices", officeList);

        List<Office> offices = roomDao.listOfficeByCond(cmd);
        for (Office office1 : offices) {
            // office1.setRooms(roomDao.list(office1.getId()));
            office1.setRoomOffices(roomDao.listRoomOfficeByOid(office1.getId()));
        }
        cmd.setOffices(offices);
        return model;
    }

    @Transactional
    @Override
    public void resetUserSeat(UserSeatResetCommand cmd, BindException errors)
            throws Exception {
        Admission ad = admissionDao.get(cmd.getUserId());
        if (ad != null && ad.isPrintFlg() && !resetAdmissionAfterPrint) {
            throw new Exception("考生已经打印准考证号，无法重新生成准考证号。");
        }
        roomDao.removeSeatByUids(Arrays.asList(new Integer[] { cmd.getUserId() }));
        admissionDao
                .deleteByUids(Arrays.asList(new Integer[] { cmd.getUserId() }));
        if (cmd.getSeatCode() != null && cmd.getSeatCode().length() > 0
                && cmd.getRoomId() != null && cmd.getRoomId() > -1) {
            Exam exam = examDao.list().get(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            String prefix = sdf.format(exam.getExamDate());
            Seat saveSeat = new Seat();
            saveSeat.setCode(cmd.getSeatCode());
            saveSeat.setUserId(cmd.getUserId());
            saveSeat.setRoomId(cmd.getRoomId());
            Admission saveAdmission = new Admission(cmd.getUserId(), prefix
                    + cmd.getRoomCode() + saveSeat.getCode());
            Seat old = roomDao.getSeatByRidAndCode(cmd.getRoomId(),
                    cmd.getSeatCode());
            if (old != null) {
                roomDao.removeSeatByUids(Arrays.asList(new Integer[] { old
                        .getUserId() }));
                admissionDao.deleteByUids(Arrays.asList(new Integer[] { old
                        .getUserId() }));
            }
            roomDao.addSeat(saveSeat);
            admissionDao.add(saveAdmission);
        }
        Seat seat = roomDao.getSeat(cmd.getUserId());
        cmd.setSeat(seat);
        if (seat != null) {
            cmd.setSeatCode(seat.getCode());
            cmd.setSeatId(seat.getId());
            cmd.setRoomId(seat.getRoomId());
            if (seat.getRoom() != null) {
                cmd.setRoomCode(seat.getRoom().getCode());
            }
        }
        Admission admission = admissionDao.get(cmd.getUserId());
        cmd.setAdmission(admission);
    }

    @Override
    public void initSeatReset(UserSeatResetCommand cmd, BindException errors)
            throws Exception {
        String idCardNo = cmd.getIdCardNo();
        User user = userDao.getByIdCardNo(idCardNo);
        if (user == null) {
            errors.rejectValue("idCardNo", "required.idCardNo", "身份证号不正确!");
            return;
        }
        List<Apply> applyList = applyDao.findApplyInfo(user.getId());
        if (applyList == null || applyList.isEmpty()) {
            errors.rejectValue("idCardNo", "required.idCardNo", "找不到该考生的报名信息!");
            return;
        }
        Apply a = applyList.get(0);
        if (a.getState() != 2) {
            errors.rejectValue("idCardNo", "required.idCardNo",
                    "该考生未通过审核,无法分配座位!");
            return;
        }
        cmd.setUser(user);
        cmd.setUserId(user.getId());
        cmd.setApply(a);
        Seat seat = roomDao.getSeat(user.getId());
        cmd.setSeat(seat);
        if (seat != null) {
            cmd.setSeatCode(seat.getCode());
            cmd.setSeatId(seat.getId());
            cmd.setRoomId(seat.getRoomId());
            if (seat.getRoom() != null) {
                cmd.setRoomCode(seat.getRoom().getCode());
            }
        }
        Admission admission = admissionDao.get(user.getId());
        cmd.setAdmission(admission);
        cmd.setRooms(roomDao.listRoom());
    }

    @Transactional
    @Override
    public void deleteRoom(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception {
        if (!roomDao.checkAdmission(cmd)) {
            throw new Exception("已有考生打印准考证，无法删除");
        }
        Room r = roomDao.getRoom(cmd.getRoomId());
        roomDao.removeSeatByRid(r.getId());
        roomDao.removeRoomOfficeByRid(r.getId());
        roomDao.removeRoom(r);
    }

    @Transactional
    @Override
    public void removeAssign(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception {
        if (!roomDao.checkAdmission(cmd)) {
            throw new Exception("已有考生打印准考证，无法取消分配");
        }
        RoomOffice ro = roomDao.getRoomOffice(cmd.getRoomOfficeId());
        Room r = roomDao.getRoom(ro.getRoomId());
        r.setRemainSeats(r.getRemainSeats() + ro.getAssignSeats());
        roomDao.removeRoomOffice(ro);
        roomDao.updateRoom(r);
    }

    /**
     * @return the roomDao
     */
    public RoomDao getRoomDao() {
        return roomDao;
    }

    /**
     * @param roomDao
     *            the roomDao to set
     */
    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @return the departDao
     */
    public DepartDao getDepartDao() {
        return departDao;
    }

    /**
     * @param departDao
     *            the departDao to set
     */
    public void setDepartDao(DepartDao departDao) {
        this.departDao = departDao;
    }

    /**
     * @return the officeDao
     */
    public OfficeDao getOfficeDao() {
        return officeDao;
    }

    /**
     * @param officeDao
     *            the officeDao to set
     */
    public void setOfficeDao(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    /**
     * @return the applyDao
     */
    public ApplyDao getApplyDao() {
        return applyDao;
    }

    /**
     * @param applyDao
     *            the applyDao to set
     */
    public void setApplyDao(ApplyDao applyDao) {
        this.applyDao = applyDao;
    }

    /**
     * @return the examDao
     */
    public ExamDao getExamDao() {
        return examDao;
    }

    /**
     * @param examDao
     *            the examDao to set
     */
    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    /**
     * @return the admissionDao
     */
    public AdmissionDao getAdmissionDao() {
        return admissionDao;
    }

    /**
     * @param admissionDao
     *            the admissionDao to set
     */
    public void setAdmissionDao(AdmissionDao admissionDao) {
        this.admissionDao = admissionDao;
    }

    /**
     * @return the resetAdmissionAfterPrint
     */
    public boolean isResetAdmissionAfterPrint() {
        return resetAdmissionAfterPrint;
    }

    /**
     * @param resetAdmissionAfterPrint
     *            the resetAdmissionAfterPrint to set
     */
    public void setResetAdmissionAfterPrint(boolean resetAdmissionAfterPrint) {
        this.resetAdmissionAfterPrint = resetAdmissionAfterPrint;
    }

    public int getSeatsPerRoom() {
        return seatsPerRoom;
    }

    public void setSeatsPerRoom(int seatsPerRoom) {
        this.seatsPerRoom = seatsPerRoom;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map initRoomAssign(RoomEditCommand cmd, Errors errors)
            throws Exception {

        Map model = new HashMap();
        List<Depart> deptList = departDao.list();
        model.put("departs", deptList);

        List<Office> officeList = roomDao.listOfficeByCond(new RoomEditCommand());
        model.put("offices", officeList);
        model.put("rooms", roomDao.listRoom());
        return model;
    }

    @Transactional
    @Override
    public void assignRoom(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception {
        Room r = roomDao.getRoom(cmd.getRoomId());
        if (r == null) {
            errors.rejectValue("roomId", "roomId", "指定的考场不存在");
            return;
        }
        int assignSeats = Integer.parseInt(cmd.getSeats());
        if (assignSeats > r.getRemainSeats()) {
            errors.rejectValue("seats", "seats", "座位数超过了指定考场的剩余座位数");
            return;
        }
        Office o = officeDao.get(cmd.getOfficeId());
        RoomOffice ro = addRoomOffice(o, r, assignSeats);
        roomDao.updateRoom(r);
        roomDao.addRoomOffice(Arrays.asList(new RoomOffice[] { ro }));
    }

}
