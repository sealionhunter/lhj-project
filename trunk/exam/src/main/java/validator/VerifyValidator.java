package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import command.VerifyCommand;

public class VerifyValidator implements Validator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(VerifyCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        VerifyCommand cmd = (VerifyCommand) target;
        String reason = cmd.getVerifyReason();
        Integer state = Integer.valueOf(cmd.getVerifyState());
        boolean submitFlag = Boolean.valueOf(cmd.getSubmitFlag());

        if (submitFlag && state == 1) {
            if (reason == null || reason.trim().isEmpty()) {
                errors.rejectValue("verifyReason", "verifyReason",
                        "不通过的原因必须填写!");
            }
        }
    }

}
