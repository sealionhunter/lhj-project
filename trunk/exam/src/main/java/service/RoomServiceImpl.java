package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @SuppressWarnings("rawtypes")
    @Override
    public Map initEdit(RoomEditCommand cmd, String id, Errors errors)
            throws Exception {
        cmd.setOffices(officeDao.list());
        cmd.setDeparts(departDao.list());
        if (id != null && id.length() > 0) {
            Room room = roomDao.get(Integer.valueOf(id));
            if (room != null) {
                cmd.setId(room.getId());
                cmd.setCode(room.getCode());
                cmd.setName(room.getName());
                cmd.setPosition(room.getPosition());
                cmd.setSeats(String.valueOf(room.getSeats()));
                Integer officeId = room.getOfficeId();
                cmd.setOfficeId(officeId);
                if (officeId != null) {
                    for (Office office : cmd.getOffices()) {
                        if (office.getId() == officeId) {
                            cmd.setDepartId(office.getDepartId());
                            break;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void editOk(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception {
        cmd.setOffices(officeDao.list());
        cmd.setDeparts(departDao.list());
        if (roomDao.checkRoom(cmd.getCode())) {
            errors.rejectValue("code", "code", "指定编号的考场已存在，请重新指定");
            return;
        }
        Room room = new Room();
        room.setId(cmd.getId());
        room.setCode(cmd.getCode());
        room.setName(cmd.getName());
        room.setPosition(cmd.getPosition());
        room.setSeats(Integer.parseInt(cmd.getSeats()));
        room.setOfficeId(cmd.getOfficeId());
        roomDao.update(room);
    }

    @Transactional
    @Override
    public void generateAdmission(HttpServletRequest request,
            RoomEditCommand cmd, BindException errors) throws Exception {
        Exam exam = examDao.list().get(0);

        SignUpPersonSearchCommand condition = new SignUpPersonSearchCommand();
        condition.setState(2);
        condition.setDeptId(cmd.getDepartId());
        condition.setPostId(cmd.getOfficeId());

        checkCondition(exam, cmd);

        List<Apply> applies = applyDao.list(condition);
        List<Admission> admissions = new ArrayList<Admission>(applies.size());
        List<Seat> seats = new ArrayList<Seat>(applies.size());
        List<Room> rooms = roomDao.list();

        // yyMMddAABB
        // TODO
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String prefix = sdf.format(exam.getExamDate());
        Map<String, Integer> sequeses = new HashMap<String, Integer>();
        // TODO
        Room room = null;
        for (Apply apply : applies) {
            room = null;
            for (Room r : rooms) {
                String key = apply.getOffice().getCode() + "room" + r.getId();
                if (r.getOfficeId() == apply.getId().getOfficeid()
                        && (!sequeses.containsKey(key) || sequeses.get(key) < r
                                .getSeats())) {
                    room = r;
                    break;
                }
            }
            if (room == null) {
                continue;
            }
            String key = apply.getOffice().getCode() + "room" + room.getId();
            Integer seq = sequeses.get(key);
            if (seq == null) {
                seq = 1;
            } else {
                seq += 1;
            }
            sequeses.put(key, seq);
            Seat seat = new Seat();
            seat.setCode(String.format("%02d", seq));
            seat.setRoomId(room.getId());
            seat.setUserId(apply.getId().getUserid());
            seats.add(seat);
            admissions.add(new Admission(apply.getUser().getId(), prefix
                    + room.getCode() + seat.getCode()));
            roomDao.removeSeat(apply.getId().getUserid());
            admissionDao.delete(apply.getId().getUserid());
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

        List<Office> offices = roomDao.findOfficeInfo(cmd);
        for (Office office1 : offices) {
            office1.setRooms(roomDao.list(office1.getId()));
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
        roomDao.removeSeat(cmd.getUserId());
        admissionDao.delete(cmd.getUserId());
        Exam exam = examDao.list().get(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String prefix = sdf.format(exam.getExamDate());
        Seat seat = new Seat();
        seat.setCode(cmd.getSeatId());
        seat.setUserId(cmd.getUserId());
        seat.setRoomId(cmd.getRoomId());
        Admission admission = new Admission(cmd.getUserId(), prefix
                + cmd.getRoomCode() + seat.getCode());
        roomDao.addSeat(seat);
        admissionDao.add(admission);
        seat = roomDao.getSeat(cmd.getUserId());
        cmd.setSeat(seat);
        if (seat != null) {
            cmd.setSeatId(seat.getCode());
            cmd.setRoomId(seat.getRoomId());
            if (seat.getRoom() != null) {
                cmd.setRoomCode(seat.getRoom().getCode());
            }
        }
        admission = admissionDao.get(cmd.getUserId());
        if (admission != null) {
            cmd.setAdmission(admission.getCode());
        }
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
            errors.rejectValue("idCardNo", "required.apply", "找不到该考生的报名信息");
            return;
        }
        Apply a = applyList.get(0);
        cmd.setUser(user);
        cmd.setUserId(user.getId());
        cmd.setApply(a);
        Seat seat = roomDao.getSeat(user.getId());
        cmd.setSeat(seat);
        String seatCode = "";
        if (seat != null) {
            cmd.setSeatId(seat.getCode());
            seatCode = seat.getCode();
            cmd.setRoomId(seat.getRoomId());
            if (seat.getRoom() != null) {
                cmd.setRoomCode(seat.getRoom().getCode());
            }
        }
        Admission admission = admissionDao.get(user.getId());
        if (admission != null) {
            cmd.setAdmission(admission.getCode());
        }
        List<Room> rooms = roomDao.list(a.getOffice().getId());
        for (Room room : rooms) {
            List<Seat> dbSeats = roomDao.findSeat(room.getId());
            List<Seat> avliableSeats = new ArrayList<Seat>();
            for (int i = 1; i <= room.getSeats(); i++) {
                boolean findDbSeat = false;
                for (Seat dbSeat : dbSeats) {
                    if (i == Integer.parseInt(dbSeat.getCode())
                            && !seatCode.equals(dbSeat.getCode())) {
                        findDbSeat = true;
                        break;
                    }
                }
                if (findDbSeat) {
                    continue;
                }

                Seat avliable = new Seat();
                avliable.setCode(String.format("%02d", i));
                avliable.setRoomId(room.getId());
                avliable.setUserId(user.getId());
                avliableSeats.add(avliable);
            }
            room.setSeatsSet(avliableSeats);
        }
        cmd.setRooms(rooms);
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
     * @param resetAdmissionAfterPrint the resetAdmissionAfterPrint to set
     */
    public void setResetAdmissionAfterPrint(boolean resetAdmissionAfterPrint) {
        this.resetAdmissionAfterPrint = resetAdmissionAfterPrint;
    }

}
