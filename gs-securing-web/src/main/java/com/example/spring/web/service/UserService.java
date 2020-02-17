package com.example.spring.web.service;

import com.example.spring.db.domain.Role;
import com.example.spring.db.domain.User;
import com.example.spring.db.repository.RoleRepository;
import com.example.spring.db.repository.UserRepository;
import com.example.spring.enums.RoleType;
import com.example.spring.web.dto.UserDTO;
import com.example.spring.web.exception.EmailExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User registerNewUserAccount(final UserDTO accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with the email address already: "
                    + accountDto.getEmail());
        }

        final User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setEmail(accountDto.getEmail());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        final Role userRole = roleRepository.findByName(RoleType.ROLE_USER);
        user.setRoles(Arrays.asList(userRole));

        logger.info("Persisting user with details {}", user);
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return Objects.nonNull(userRepository.findByEmail(email));
    }
}
