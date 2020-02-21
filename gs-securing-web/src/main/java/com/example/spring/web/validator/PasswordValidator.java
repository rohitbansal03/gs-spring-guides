package com.example.spring.web.validator;

import com.example.spring.annotation.PasswordConstraint;
import com.example.spring.web.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, Object> {

    @Override
    public boolean isValid(Object userObject, ConstraintValidatorContext arg1) {
        UserDTO user = (UserDTO) userObject;
        if(Objects.isNull(user.getPassword())) {
            return true;
        }
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
