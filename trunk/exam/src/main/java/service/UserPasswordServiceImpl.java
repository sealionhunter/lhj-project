package service;

import java.util.List;

import model.Apply;
import model.User;

import org.springframework.validation.BindException;

import command.UserPasswordResetCommand;

import dao.ApplyDao;
import dao.UserDao;

public class UserPasswordServiceImpl implements UserPasswordService {
    private UserDao userDao;
    private ApplyDao applyDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ApplyDao getApplyDao() {
        return applyDao;
    }

    public void setApplyDao(ApplyDao applyDao) {
        this.applyDao = applyDao;
    }

    @Override
    public void searchUserApplyInfo(UserPasswordResetCommand cmd,
            BindException errors) throws Exception {
        User u = userDao.getByIdCardNo(cmd.getIdCardNo());
        if (u == null) {
            errors.rejectValue("idCardNo", "required.idCardNo", "身份证号不正确!");
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
    public void resetUserPasswor(String idCardNo, String initPassword)
            throws Exception {
        User u = userDao.getByIdCardNo(idCardNo);
        u.setPassword(initPassword);
        userDao.update(u);
    }
}
