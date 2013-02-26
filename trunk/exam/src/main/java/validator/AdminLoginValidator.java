package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import command.AdminLoginCommand;

public class AdminLoginValidator implements Validator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(AdminLoginCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AdminLoginCommand cmd = (AdminLoginCommand) target;
        if (cmd.getAdminId() == null || cmd.getAdminId().trim().isEmpty()) {
            errors.rejectValue("adminId", "adminId.required", "请输入管理员帐号");
        }
    }

}
