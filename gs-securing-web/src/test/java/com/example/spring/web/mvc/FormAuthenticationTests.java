package com.example.spring.web.mvc;

import com.example.spring.db.domain.User;
import com.example.spring.db.repository.UserRepository;
import com.example.spring.utils.TestHelper;
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
public class FormAuthenticationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        final User user = TestHelper.prepareUser(passwordEncoder);
        Mockito.when(userRepository.findByEmail(Mockito.eq(TestHelper.EMAIL_ADDRESS))).thenReturn(user);

        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login =
                formLogin().user(TestHelper.EMAIL_ADDRESS).password(TestHelper.RAW_PASSWORD);
        mockMvc.perform(login).andExpect(authenticated().withUsername(TestHelper.EMAIL_ADDRESS));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin().user("invalid").password(
                "invalidpassword");
        mockMvc.perform(login).andExpect(unauthenticated());
    }

}
