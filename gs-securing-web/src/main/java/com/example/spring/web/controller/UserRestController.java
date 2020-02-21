package com.example.spring.web.controller;

import com.example.spring.db.repository.RoleRepository;
import com.example.spring.web.dto.UserDTO;
import com.example.spring.web.exception.EmailExistsException;
import com.example.spring.web.handler.WebUtil;
import com.example.spring.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class UserRestController extends AbstractRestController {

    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Object> handleEmailExistsException(HttpServletRequest httpRequest, EmailExistsException ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return WebUtil.getErrorResponse(Arrays.asList(ex.getMessage()), httpHeaders, HttpStatus.CONFLICT);
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        logger.debug("Request for fetching list of users");
        return userService.getUsers();
    }

    @GetMapping("/roles")
    public List<String> getRoles() {
        logger.debug("Request for fetching role-types");
        return roleRepository.findDistinctRoles();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody @Validated UserDTO userDTO) throws EmailExistsException {
        logger.info("Request for adding user {}", userDTO);
        userService.addUserAccount(userDTO);
    }

}
