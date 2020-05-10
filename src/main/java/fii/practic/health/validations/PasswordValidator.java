package fii.practic.health.validations;

import fii.practic.health.boundry.util.DoctorConstants;
import fii.practic.health.entity.model.Patient;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Patient patient = (Patient) target;
        if (!patient.getUser().getPassword().equals(patient.getUser().getConfirmPassword())) {
            errors.rejectValue("user.password", DoctorConstants.PASSWORD_NOTMATCH_VALIDATION);
        }
    }
}

