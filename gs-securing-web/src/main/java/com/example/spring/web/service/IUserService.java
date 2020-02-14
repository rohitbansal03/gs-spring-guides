package com.example.spring.web.service;

import com.example.spring.db.domain.User;
import com.example.spring.web.dto.UserDTO;
import com.example.spring.web.exception.EmailExistsException;

public interface IUserService {

    User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;

}
