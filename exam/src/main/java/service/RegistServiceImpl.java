package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Admin;
import model.Admission;
import model.Apply;
import model.ApplyPK;
import model.City;
import model.Depart;
import model.Exam;
import model.Master;
import model.Office;
import model.User;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import command.AdminLoginCommand;
import command.AdminModifyCommand;
import command.AdmissionCommand;
import command.ModifyPasswordCommand;
import command.RegistCommand;
import command.SignUpPersonSearchCommand;
import command.StatusSearchCommand;
import command.VerifyCommand;

import dao.AdminDao;
import dao.AdmissionDao;
import dao.ApplyDao;
import dao.CityDao;
import dao.DepartDao;
import dao.ExamDao;
import dao.MasterDao;
import dao.OfficeDao;
import dao.UserDao;

public class RegistServiceImpl implements RegistService {

    private UserDao userDao;
    private DepartDao departDao;
    private OfficeDao officeDao;
    private CityDao cityDao;
    private ApplyDao applyDao;
    private MasterDao masterDao;
    private ExamDao examDao;
    private AdminDao adminDao;
    private AdmissionDao admissionDao;

    @Override
    @Transactional
    public void add(RegistCommand object) throws Exception {
        User user = new User();
        user.setName(object.getName());
        user.setPassword(object.getPassword());
        user.setIdCardNo(object.getIdCardNo());
        user.setSex(Integer.parseInt(object.getSexCode()));
        user.setNationCode(Integer.parseInt(object.getNationCode()));
        user.setHomeTown(object.getHomeTown());
        user.setBirthdayMonth(object.getBirthdayMonth());
        user.setBirthdayYear(object.getBirthdayYear());
        user.setPoliticalCode(Integer.parseInt(object.getPoliticalCode()));
        user.setIdentity(object.getIdentity());
        user.setMarried(Integer.parseInt(object.getIsMarried()));
        user.setDegree(object.getDegree());
        user.setGraduateSchool(object.getUserSchool());
        user.setGraduateYear(Integer.parseInt(object.getGraduateYear()));
        user.setGraduateMonth(Integer.parseInt(object.getGraduateMonth()));
        user.setWorkyears(Integer.valueOf(object.getWorkYears()));
        user.setMajor(object.getUserMajor());
        user.setComputerSkill(object.getComputerSkill());
        user.setLanguageSkill(object.getLanguageSkill());
        user.setTelephone(object.getTelephone());
        user.setHeight(Integer.parseInt(object.getHeight()));
        user.setTrainingExp(object.getTrainingExp());
        user.setWorkExp(object.getWorkExp());
        user.setSocialRel(object.getSocialRel());
        user.setPhoto(object.getPhoto());
        Integer userId = object.getUserId();
        String reason = "";
        if (object.getUserId() == null) {
            userId = getUserDao().add(user);
            object.setUserId(userId);
        } else {
            user.setId(object.getUserId());
            getUserDao().update(user);
            List<Apply> applies = getApplyDao().find(userId);
            for (Apply apply : applies) {
                reason = apply.getReason();
                getApplyDao().delete(apply);
            }
        }
        Apply apply = new Apply();
        ApplyPK applyPk = new ApplyPK();
        applyPk.setUserid(userId);
        applyPk.setOfficeid(Integer.valueOf(object.getApplyPostId()));
        apply.setId(applyPk);
        apply.setState(0);
        apply.setReason(reason);
        getApplyDao().add(apply);
    }

    @Override
    public List<Master> getMasters(int category) throws Exception {
        return masterDao.list(category);
    }

    @Override
    public List<Depart> listDepart() throws Exception {
        return departDao.list();
    }

    @Override
    public List<Office> listOffice() throws Exception {
        return officeDao.list();
    }

    @Override
    public List<City> listCity() throws Exception {
        return cityDao.list();
    }

    @Override
    public List<Exam> listExam() throws Exception {
        return examDao.list();
    }

    @Override
    public List<Apply> listApplyUser(SignUpPersonSearchCommand cmd)
            throws Exception {
        return applyDao.list(cmd);
    }

    @Override
    public List<User> findUser(String userName) throws Exception {
        return userDao.findUserName(userName);
    }

    @Override
    public List<User> findIdCardNo(String idCardNo) throws Exception {
        return userDao.findIdCardNo(idCardNo);
    }

    @Override
    public Apply getApply(Integer userId, Integer officeId) throws Exception {
        return applyDao.get(userId, officeId);
    }

    @Override
    public List<Apply> getApply(Integer userId) throws Exception {
        return applyDao.find(userId);
    }

    @Override
    public Office getOffice(Integer officeId) throws Exception {
        return officeDao.get(officeId);
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
     * @return the cityDao
     */
    public CityDao getCityDao() {
        return cityDao;
    }

    /**
     * @param cityDao
     *            the cityDao to set
     */
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
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
     * @return the masterDao
     */
    public MasterDao getMasterDao() {
        return masterDao;
    }

    /**
     * @param masterDao
     *            the masterDao to set
     */
    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public ExamDao getExamDao() {
        return examDao;
    }

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    /**
     * @return the adminDao
     */
    public AdminDao getAdminDao() {
        return adminDao;
    }

    /**
     * @param adminDao
     *            the adminDao to set
     */
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    /**
     * @return the admissionDao
     */
    public AdmissionDao getAdmissionDao() {
        return admissionDao;
    }

    /**
     * @param admissionDao the admissionDao to set
     */
    public void setAdmissionDao(AdmissionDao admissionDao) {
        this.admissionDao = admissionDao;
    }

    @Override
    public void modifyPassword(ModifyPasswordCommand cmd, BindException errors)
            throws Exception {
        List<User> user = userDao.findIdCardNo(cmd.getIdCardNo());
        if (user == null || user.isEmpty()) {
            errors.rejectValue("idCardNoError", "required.idCardNoError",
                    "身份证号不正确!");
            return;
        }
        User u = user.get(0);
        if (!cmd.getOldPassword().equals(u.getPassword())) {
            errors.rejectValue("oldPassword", "required.oldPassword", "旧密码不正确!");
            return;
        }
        u.setPassword(cmd.getPassword());
        userDao.update(u);

    }

    @Override
    public void searchStatus(StatusSearchCommand cmd, BindException errors)
            throws Exception {
        List<User> user = userDao.findIdCardNo(cmd.getIdCardNo());
        if (user == null || user.isEmpty()) {
            errors.rejectValue("idCardNo", "required.idCardNo", "身份证号不正确!");
            return;
        }
        User u = user.get(0);
        if (!cmd.getPassword().equals(u.getPassword())) {
            errors.rejectValue("password", "required.password", "密码不正确!");
            return;
        }
        cmd.setUser(u);
        List<Apply> applyList = applyDao.findApplyInfo(u.getId());
        if (applyList == null || applyList.isEmpty()) {
            errors.rejectValue("idCardNo", "required.apply", "找不到该考生的报名信息");
            return;
        }
        Apply a = applyList.get(0);
        cmd.setApply(a);
    }

    @Override
    public void initVerify(VerifyCommand cmd) throws Exception {
        User user = userDao.get(cmd.getUserId());
        if (user == null) {
            throw new Exception("选择的考生不存在！");
        }
        cmd.setUser(user);
        List<Apply> applyList = applyDao.findApplyInfo(user.getId());
        if (applyList == null || applyList.isEmpty()) {
            throw new Exception("找不到该考生的报名信息");
        }
        Apply a = applyList.get(0);
        cmd.setApply(a);
        cmd.setVerifyReason(a.getReason());
        cmd.setVerifyState(String.valueOf(a.getState()));
    }

    @Override
    public void verify(Apply apply) throws Exception {
        applyDao.update(apply);
    }

    @Override
    public void adminLogin(AdminLoginCommand cmd, BindException errors)
            throws Exception {
        String adminId = cmd.getAdminId();
        String password = cmd.getAdminPassword();
        Admin admin = adminDao.get(adminId);
        if (admin == null) {
            errors.rejectValue("adminId", "adminId.error", "管理员帐号不存在！");
            return;
        }
        if (!admin.getPassword().equals(password)) {
            errors.rejectValue("adminPassword", "password.error", "密码不正确！");
            return;
        }
    }

    @Override
    public void modifyAdminPassword(AdminModifyCommand cmd, BindException errors)
            throws Exception {
        Admin admin = adminDao.get(cmd.getAdminId());
        if (admin == null) {
            errors.rejectValue("adminId", "adminId.error", "管理员帐号不存在!");
            return;
        }
        if (!cmd.getOldPassword().equals(admin.getPassword())) {
            errors.rejectValue("oldPassword", "oldPassword.error", "旧密码不正确!");
            return;
        }
        admin.setPassword(cmd.getPassword());
        adminDao.update(admin);
    }

    @Override
    public void printAdmission(AdmissionCommand cmd, BindException errors) throws Exception {
        Exam exam = examDao.list().get(0);

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
            throw new Exception("报名还未开始，不能打印准考证");
        }
        if (now.before(exam.getApplyDeadDate())) {
            throw new Exception("报名还未结束，不能打印准考证");
        }
        if (now.after(toTime)) {
            throw new Exception("考试已经结束，不能打印准考证");
        }
        if (now.after(fromTime)) {
            throw new Exception("考试已经开始，不能打印准考证");
        }

        Admission admission = admissionDao.get(cmd.getUser().getId());
        if (admission == null) {
            throw new Exception("管理员还未分配座位，请等待座位分配完毕，再打印准考证");
        }
        cmd.setExam(exam);
        cmd.setAdmission(admission);
    }
}
