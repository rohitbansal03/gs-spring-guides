package com.example.spring.web.service;

import com.example.spring.db.domain.User;
import com.example.spring.web.dto.UserDTO;
import com.example.spring.web.exception.EmailExistsException;

import java.util.List;

public interface IUserService {

    UserDTO getUserDTO(String emailAddress);

    User addUserAccount(UserDTO accountDto) throws EmailExistsException;

    List<UserDTO> getUsers();

}
