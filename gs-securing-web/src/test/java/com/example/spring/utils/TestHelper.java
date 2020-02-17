package com.example.spring.utils;

import com.example.spring.db.domain.Role;
import com.example.spring.db.domain.User;
import com.example.spring.enums.RoleType;
import com.example.spring.web.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class TestHelper {

    public static final String RAW_PASSWORD = "password";
    public static final String EMAIL_ADDRESS = "john.doe@google.com";

    public static UserDTO prepareUserDTO() {
        final UserDTO user = new UserDTO();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setMatchingPassword("password");
        return user;
    }

    public static User prepareUser(PasswordEncoder passwordEncoder) {
        final User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail(EMAIL_ADDRESS);
        user.setPassword(passwordEncoder.encode(RAW_PASSWORD));
        assert passwordEncoder.matches(RAW_PASSWORD, user.getPassword());

        final Role role = new Role(RoleType.ROLE_USER);
        user.setRoles(Arrays.asList(role));
        return user;
    }

}
