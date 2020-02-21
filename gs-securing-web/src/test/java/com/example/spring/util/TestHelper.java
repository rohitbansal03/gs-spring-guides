package com.example.spring.util;

import com.example.spring.db.domain.Role;
import com.example.spring.db.domain.User;
import com.example.spring.enums.RoleType;
import com.example.spring.web.dto.UserDTO;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

public class TestHelper {

    public static List<UserDTO> prepareUsers() {
        UserDTO user1 = prepareUserDTO("John", "Doe", "john@invalid.com");
        UserDTO user2 = prepareUserDTO("Jane", "Doe", "jane@invalid.com");
        return Arrays.asList(user1, user2);
    }

    public static UserDTO prepareUserDTO(String firstName, String lastName, String emailAddress) {
        final UserDTO user = new UserDTO();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(emailAddress);
        user.setPassword("password");
        user.setMatchingPassword("password");
        user.setRole(RoleType.ROLE_USER);
        return user;
    }

    public static User prepareUser(PasswordEncoder passwordEncoder, String rawPassword, RoleType roleType) {
        final User user = Mockito.mock(User.class);
        Mockito.when(user.getFirstName()).thenReturn("John");
        Mockito.when(user.getLastName()).thenReturn("Doe");
        Mockito.when(user.getEmail()).thenReturn("john@invalid.com");
        Mockito.when(user.getPassword()).thenReturn(passwordEncoder.encode(rawPassword));
        Mockito.when(user.getRoles()).thenReturn(Arrays.asList(new Role(roleType)));
        return user;
    }

}
