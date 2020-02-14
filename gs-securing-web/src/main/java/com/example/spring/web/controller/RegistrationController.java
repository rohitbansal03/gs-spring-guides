package com.example.spring.web.controller;

import com.example.spring.db.domain.User;
import com.example.spring.web.dto.UserDTO;
import com.example.spring.web.exception.EmailExistsException;
import com.example.spring.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(("/register"))
public class RegistrationController {

    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private IUserService userService;

    @GetMapping
    public String showRegistrationForm(HttpServletRequest request, Model model) {
        logger.debug("Returning registration page ...");
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDTO accountDto,
                                      BindingResult result, HttpServletRequest request, Errors errors) {
        if (result.hasErrors()) {
            return "registration";
        }
        Optional<User> registered = this.createUserAccount(accountDto, result);
        if (!registered.isPresent()) {
            result.rejectValue("email", "message.regError");
            return "registration";
        }
        return "redirect:/login";
    }

    private Optional<User> createUserAccount(UserDTO userDTO, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(userDTO);
            logger.info("Registration successful with email address {}", userDTO.getEmail());
        } catch (EmailExistsException e) {
            logger.info("Email already exists", e);
            return Optional.empty();
        }
        return Optional.of(registered);
    }

}
