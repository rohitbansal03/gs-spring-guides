package com.example.spring.rest.controller;

import com.example.spring.db.domain.User;
import com.example.spring.db.repository.RoleRepository;
import com.example.spring.db.repository.UserRepository;
import com.example.spring.util.TestHelper;
import com.example.spring.web.dto.UserDTO;
import com.example.spring.web.service.CustomUserDetailsService;
import com.example.spring.web.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
public class UserRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void testSecuredAccess() throws Exception {
        final List<UserDTO> users = TestHelper.prepareUsers();
        Mockito.when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(get("/rest/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUnauthenticatedAccess() throws Exception {
        mockMvc.perform(get("/rest/users"))
                .andExpect(status().isUnauthorized());
    }

}
