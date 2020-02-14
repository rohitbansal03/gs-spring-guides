package com.example.spring.db;

import com.example.spring.db.domain.Role;
import com.example.spring.db.domain.User;
import com.example.spring.db.repository.RoleRepository;
import com.example.spring.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class LoadInitialDataRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(final String... args) throws Exception {
        final Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        final Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        final User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setEmail("john@google.com");
        john.setPassword(passwordEncoder.encode("password"));
        john.setRoles(Arrays.asList(roleUser));

        userRepository.save(john);
    }
}
