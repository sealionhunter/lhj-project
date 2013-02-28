package service;

import org.springframework.validation.BindException;

import command.UserPasswordResetCommand;

public interface UserPasswordService {
    void searchUserApplyInfo(UserPasswordResetCommand cmd, BindException errors)
            throws Exception;

    void resetUserPasswor(String idCardNo, String initPassword)
            throws Exception;
}
