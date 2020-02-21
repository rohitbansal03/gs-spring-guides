package com.example.spring.web.service;

import com.example.spring.db.domain.Role;
import com.example.spring.db.domain.User;
import com.example.spring.db.repository.RoleRepository;
import com.example.spring.db.repository.UserRepository;
import com.example.spring.web.dto.UserDTO;
import com.example.spring.exception.EmailExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO getUserDTO(String emailAddress) {
        final User user = userRepository.findByEmail(emailAddress);
        return this.getUserDTO(user);
    }

    @Transactional
    @Override
    public User addUserAccount(final UserDTO userDTO)
            throws EmailExistsException {

        if (emailExist(userDTO.getEmail())) {
            throw new EmailExistsException("There is an account with the email address already: "
                    + userDTO.getEmail());
        }
        final User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        final Role userRole = roleRepository.findByName(userDTO.getRole());
        user.setRoles(Arrays.asList(userRole));

        logger.info("Persisting user with details {}", user);
        return userRepository.save(user);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserDTO userDTO = getUserDTO(user);
            userDTOS.add(userDTO);
        });
        return userDTOS;
    }

    private UserDTO getUserDTO(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRoles().stream().findFirst().get().getName());
        return userDTO;
    }

    private boolean emailExist(String email) {
        return Objects.nonNull(userRepository.findByEmail(email));
    }
}
