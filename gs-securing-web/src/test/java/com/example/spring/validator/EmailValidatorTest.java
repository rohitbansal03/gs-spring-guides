package com.example.spring.validator;

import com.example.spring.utils.TestHelper;
import com.example.spring.web.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class EmailValidatorTest {

    private UserDTO user;

    @Autowired
    private Validator validator;

    @BeforeEach
    void beforeAll() {
        user = TestHelper.prepareUserDTO();
    }

    @Test
    public void checkUserWithEmptyEmail() {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        final List<String> violationMessages =
                violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertTrue(violationMessages.contains("Email address cannot be left blank"),
                violationMessages.get(0));
    }

    @Test
    public void checkUserWithInvalidEmail() {
        user.setEmail("john@");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        final List<String> violationMessages =
                violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertTrue(violationMessages.contains("Invalid email-address provided"));
    }

}
