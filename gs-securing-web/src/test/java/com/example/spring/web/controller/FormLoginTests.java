package com.example.spring.web.controller;

import com.example.spring.db.domain.User;
import com.example.spring.db.repository.UserRepository;
import com.example.spring.enums.RoleType;
import com.example.spring.util.TestHelper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@SpringBootTest
@AutoConfigureMockMvc
public class FormLoginTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLoginWithValidUser() throws Exception {

        final String rawPassword = "Passw0rd";
        final User user = TestHelper.prepareUser(passwordEncoder, rawPassword, RoleType.ROLE_USER);
        Mockito.when(userRepository.findByEmail(Mockito.eq(user.getEmail()))).thenReturn(user);

        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login =
                formLogin().user(user.getEmail()).password(rawPassword);
        mockMvc.perform(login).andExpect(authenticated().withUsername(user.getEmail()));
    }

    @Test
    public void testLoginWithInvalidUser() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("invalid").password("invalidpassword");
        mockMvc.perform(login).andExpect(unauthenticated());
    }

}
