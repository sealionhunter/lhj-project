package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import command.ScoreInputCommand;

public class ScoreInputValidator implements Validator {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(ScoreInputCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ScoreInputCommand cmd = (ScoreInputCommand) target;
        Boolean flag = Boolean.valueOf(cmd.getSubmitFlag());
        if (!flag) {
            return;
        }

        if (cmd.getIdCardNo() == null || cmd.getIdCardNo().trim().isEmpty()) {
            errors.rejectValue("idCardNo", "idCardNo.required", "请输入身份证号");
        }

        if (cmd.getAdmissionId() == null || cmd.getAdmissionId().trim().isEmpty()) {
            errors.rejectValue("admissionId", "admissionId.required", "请输入准考证号");
        }

        if (cmd.getScore() == null || cmd.getScore().trim().isEmpty()) {
            errors.rejectValue("score", "score.required", "请输入成绩分数");
        } else {
            try {
                Double.parseDouble(cmd.getScore());
            } catch (NumberFormatException e) {
                errors.rejectValue("score", "score.error", "输入的分数格式不正确");
            }
        }
    }
}
