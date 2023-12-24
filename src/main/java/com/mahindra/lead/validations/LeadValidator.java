package com.mahindra.lead.validations;

import com.mahindra.lead.dto.LeadDTO;
import com.mahindra.lead.model.Lead;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class LeadValidator implements Validator {

    private static final String ALPHABET_PATTERN = "^[A-Za-z]+$";
    private static final String MOBILE_NUMBER_PATTERN = "^[6-9]\\d{9}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return Lead.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LeadDTO lead = (LeadDTO) target;
        System.out.println(lead);
        validateLeadId(lead.getLeadId(), errors);
        validateFirstName(lead.getFirstName(), errors);
        validateLastName(lead.getLastName(), errors);
        validateMobileNumber(lead.getMobileNumber(), errors);
        validateGender(lead.getGender(), errors);
        validateDob(lead.getDob(), errors);
        validateEmail(lead.getEmail(), errors);
    }

    private void validateLeadId(Integer leadId, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "leadId", "leadId.empty", "Lead ID is mandatory");
        if (leadId != null && leadId <= 0) {
            errors.rejectValue("leadId", "leadId.invalid", "Lead ID should be a positive integer");
        }
    }

    private void validateFirstName(String firstName, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty", "First Name is mandatory");
        if (!Pattern.matches(ALPHABET_PATTERN, firstName)) {
            errors.rejectValue("firstName", "firstName.invalid", "First Name should contain only alphabets");
        }
    }



    private void validateLastName(String lastName, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty", "Last Name is mandatory");
        if (!Pattern.matches(ALPHABET_PATTERN, lastName)) {
            errors.rejectValue("lastName", "lastName.invalid", "Last Name should contain only alphabets");
        }
    }

    private void validateMobileNumber(String mobileNumber, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "mobileNumber", "mobileNumber.empty", "Mobile Number is mandatory");
        if (!Pattern.matches(MOBILE_NUMBER_PATTERN, mobileNumber)) {
            errors.rejectValue("mobileNumber", "mobileNumber.invalid", "Mobile Number should be a 10-digit number, starting with a digit between 6 and 9");
        }
    }

    private void validateGender(String gender, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "gender", "gender.empty", "Gender is mandatory");
        if (!Pattern.matches("^(Male|Female|Others)$", gender)) {
            errors.rejectValue("gender", "gender.invalid", "Gender should be Male, Female, or Others");
        }
    }

    private void validateDob(LocalDate dob, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "dob", "dob.empty", "Date of Birth is mandatory");
        if (dob != null && dob.isAfter(LocalDate.now())) {
            errors.rejectValue("dob", "dob.invalid", "Date of Birth should be in the past");
        }
    }

    private void validateEmail(String email, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "email", "email.empty", "Email is mandatory");
        if (!Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", email)) {
            errors.rejectValue("email", "email.invalid", "Invalid email format");
        }
    }
}
