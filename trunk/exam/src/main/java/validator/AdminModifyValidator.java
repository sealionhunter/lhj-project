package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import command.AdminModifyCommand;

public class AdminModifyValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(AdminModifyCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AdminModifyCommand cmd = (AdminModifyCommand) target;
        String adminId = cmd.getAdminId();
        if (adminId == null || adminId.isEmpty()) {
            errors.rejectValue("adminId", "admin.required", "请输入管理员帐号");
        }
        if (cmd.getOldPassword() == null || cmd.getOldPassword().length() == 0) {
            errors.rejectValue("oldPassword", "oldPassword", "旧密码必须填写!");
        }
        if (cmd.getPassword() == null || cmd.getPassword().length() == 0) {
            errors.rejectValue("password", "password", "新密码必须填写!");
        } else {
            if (!cmd.getPassword().equals(cmd.getRePassword())) {
                errors.rejectValue("rePassword", "rePassword.error",
                        "新密码与重复新密码必须一致!");
            }
        }
    }

}
