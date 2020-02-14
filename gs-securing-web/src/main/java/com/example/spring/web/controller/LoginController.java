package com.example.spring.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String loginView() {
        logger.debug("Returning login page ...");
        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(HttpServletRequest request, Model model) {
        model.addAttribute("referer", request.getHeader("Referer"));
        return "accessDenied";
    }

}
