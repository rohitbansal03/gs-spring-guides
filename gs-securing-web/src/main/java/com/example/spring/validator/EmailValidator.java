package com.example.spring.validator;

import com.example.spring.annotation.Email;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(" +
            ".[A-Za-z]{2,})$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        final boolean isValid;
        if (StringUtils.hasText(email)) {
            isValid = this.validateEmail(email);
        }
        else {
            isValid = true;
        }
        return isValid;
    }

    private boolean validateEmail(String email) {
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
