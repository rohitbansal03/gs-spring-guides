package com.example.spring.web.controller;

import com.example.spring.db.domain.User;
import com.example.spring.db.repository.RoleRepository;
import com.example.spring.web.dto.UserDTO;
import com.example.spring.exception.EmailExistsException;
import com.example.spring.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register")
    public String showRegistrationForm(HttpServletRequest request, Model model) {
        logger.debug("Returning registration page ...");
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleRepository.findDistinctRoles());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDTO userDto,
                                      BindingResult result, HttpServletRequest request, Errors errors) {
        if (result.hasErrors()) {
            return "registration";
        }
        Optional<User> registered = this.createUserAccount(userDto, result);
        if (!registered.isPresent()) {
            result.rejectValue("email", "message.regError");
            return "registration";
        }
        return "redirect:/login";
    }

    private Optional<User> createUserAccount(UserDTO userDTO, BindingResult result) {
        User registered;
        try {
            registered = userService.addUserAccount(userDTO);
            logger.info("Registration successful with email address {}", userDTO.getEmail());
        } catch (EmailExistsException e) {
            logger.info("Email already exists", e);
            return Optional.empty();
        }
        return Optional.of(registered);
    }

}
