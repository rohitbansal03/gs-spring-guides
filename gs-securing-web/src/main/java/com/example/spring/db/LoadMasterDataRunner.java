package com.example.spring.db;

import com.example.spring.db.domain.Role;
import com.example.spring.db.repository.RoleRepository;
import com.example.spring.db.repository.UserRepository;
import com.example.spring.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration to populate master-data - `user-roles`
 */
public class LoadMasterDataRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(final String... args) {
        final long rolesCount = roleRepository.count();
        if(rolesCount == 0) {
            this.insertRole(RoleType.ROLE_USER);
            this.insertRole(RoleType.ROLE_ADMIN);
        }
    }

    private void insertRole(final RoleType roleType) {
        final Role roleUser = new Role(roleType);
        roleRepository.save(roleUser);
    }
}
