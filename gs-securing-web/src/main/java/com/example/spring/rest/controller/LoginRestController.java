package com.example.spring.rest.controller;

import com.example.spring.web.dto.UserDTO;
import com.example.spring.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginRestController extends AbstractRestController {

    private static Logger logger = LoggerFactory.getLogger(LoginRestController.class);

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public UserDTO getUserLogin(Principal user) {
        UserDTO userDTO = userService.getUserDTO(user.getName());
        return userDTO;
    }

}
