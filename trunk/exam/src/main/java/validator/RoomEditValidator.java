package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import command.RoomEditCommand;

public class RoomEditValidator implements Validator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(RoomEditCommand.class);
    }

    public void validate(Object target, Errors errors) {
        validateRoomInfo(target, errors);
    }

    private void validateRoomInfo(Object target, Errors errors) {
        RoomEditCommand v = (RoomEditCommand) target;
        if (v.getCode() == null || v.getCode().length() == 0) {
            errors.rejectValue("code", "required.code", "编号必须填写!");
        }
        if (v.getName() == null || v.getName().length() == 0) {
            errors.rejectValue("name", "required.name", "说明必须填写!");
        }
        if (v.getPosition() == null || v.getPosition().length() == 0) {
            errors.rejectValue("position", "required.position", "位置必须填写!");
        }
        if (v.getSeats() == null || v.getSeats().length() == 0) {
            errors.rejectValue("seats", "required.seats", "座位数必须填写!");
        } else if (!isDigital(v.getSeats())) {
            errors.rejectValue("seats", "required.seats", "座位数必须为数字!");
        } else {
            Integer seats = Integer.parseInt(v.getSeats());
            if (seats >= 100) {
                errors.rejectValue("seats", "required.seats",
                        "座位数必须为100以下（不含100）的整数");
            } else if (seats < 10) {
                errors.rejectValue("seats", "required.seats",
                        "座位数必须为10以上（含10）的整数");
            }
        }

    }

    public boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }

}
