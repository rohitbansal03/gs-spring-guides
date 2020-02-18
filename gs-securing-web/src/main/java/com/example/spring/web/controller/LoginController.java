package com.example.spring.web.controller;

import com.example.spring.config.CustomSuccessHandler;
import com.example.spring.web.dto.UserPrincipal;
import com.example.spring.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IUserService userService;

    @GetMapping("/")
    public String defaultView(Authentication authentication) {
        return CustomSuccessHandler.determineTargetUrl(authentication);
    }

    @GetMapping("/login")
    public String loginView() {
        logger.debug("Returning login page ...");
        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(final HttpServletRequest request, final Model model) {
        model.addAttribute("referer", request.getHeader("Referer"));
        return "accessDenied";
    }

    @GetMapping("/greeting")
    public String greeting(final HttpServletRequest request, final Model model, final Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        model.addAttribute("loggedInUser", userPrincipal.getName());
        return "greeting";
    }

    @GetMapping("/console")
    public String showConsole(final HttpServletRequest request, final Model model, final Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        model.addAttribute("loggedInUser", userPrincipal.getName());
        model.addAttribute("users", userService.getUsers());
        return "adminConsole";
    }

}
