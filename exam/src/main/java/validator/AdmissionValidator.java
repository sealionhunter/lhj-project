package validator;

import org.springframework.validation.Validator;

import command.AdmissionCommand;

public class AdmissionValidator extends StatusSearchValidator implements Validator {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(AdmissionCommand.class);
    }
}
