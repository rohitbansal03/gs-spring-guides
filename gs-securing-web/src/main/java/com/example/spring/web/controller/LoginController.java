package com.example.spring.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = {"/home"})
    public String homePageView() {
        logger.debug("Returning home page ...");
        return "home";
    }

    @GetMapping("/login")
    public String loginPageView() {
        logger.debug("Returning login page ...");
        return "login";
    }

}
