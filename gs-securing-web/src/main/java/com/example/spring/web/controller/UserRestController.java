package com.example.spring.web.controller;

import com.example.spring.db.domain.User;
import com.example.spring.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController extends AbstractRestController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

}
