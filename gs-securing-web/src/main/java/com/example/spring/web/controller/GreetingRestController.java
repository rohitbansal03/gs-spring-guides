package com.example.spring.web.controller;

import com.example.spring.web.dto.UserDTO;
import com.example.spring.web.service.GreetingService;
import com.example.spring.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GreetingRestController extends AbstractRestController {

    private static Logger logger = LoggerFactory.getLogger(GreetingRestController.class);

    private final GreetingService service;

    @Autowired
    private IUserService userService;

    public GreetingRestController(GreetingService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public UserDTO getUserLogin(Principal user) {
        UserDTO userDTO = userService.getUserDTO(user.getName());
        return userDTO;
    }

    @GetMapping("/greeting")
    public String greeting() {
        logger.debug("Sending greeting message ...");
        return service.greet();
    }

}
