package service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import command.RoomEditCommand;
import command.SignUpPersonSearchCommand;

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
        List<Office> offices = roomDao.findOfficeInfo(cmd);
        if (!offices.isEmpty()) {
            errors.reject("", "");
        }
        SignUpPersonSearchCommand condition = new SignUpPersonSearchCommand();
        condition.setState(2);
        condition.setDeptId(cmd.getDepartId());
        condition.setPostId(cmd.getOfficeId());
        List<Apply> applies = applyDao.list(condition);
        List<Admission> admissions = new ArrayList<Admission>(applies.size());
        List<Seat> seats = new ArrayList<Seat>(applies.size());
        List<Room> rooms = roomDao.list();

        // yyMMddAABB
        // TODO
        Exam exam = examDao.list().get(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String prefix = sdf.format(exam.getExamDate());
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumIntegerDigits(2);
        nf.setMinimumIntegerDigits(2);
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);
        String format = "%s%02d%02d";
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
            admissions.add(new Admission(apply.getUser().getId(), String
                    .format(format, prefix, room.getId(), seq)));
        }

        roomDao.addSeat(seats);
        admissionDao.add(admissions);
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
}
