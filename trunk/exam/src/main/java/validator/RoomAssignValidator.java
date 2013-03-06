package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import command.RoomEditCommand;

public class RoomAssignValidator implements Validator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(RoomEditCommand.class);
    }

    public void validate(Object target, Errors errors) {
        validateRoomInfo(target, errors);
    }

    private void validateRoomInfo(Object target, Errors errors) {
        RoomEditCommand v = (RoomEditCommand) target;
        if (v.getSeats() == null || v.getSeats().length() == 0) {
            errors.rejectValue("seats", "required.seats", "座位数必须填写!");
        } else if (!isDigital(v.getSeats())) {
            errors.rejectValue("seats", "required.seats", "座位数必须为数字!");
        }

    }

    public boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }

}
