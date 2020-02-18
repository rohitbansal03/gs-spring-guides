package com.example.spring.validator;

import com.example.spring.annotation.PasswordConstraint;
import com.example.spring.web.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, Object> {

    @Override
    public boolean isValid(Object userObject, ConstraintValidatorContext arg1) {
        UserDTO user = (UserDTO) userObject;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
