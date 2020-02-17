package com.example.spring.web.service;

import com.example.spring.db.domain.User;
import com.example.spring.db.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final boolean enabled = true;
    private final boolean accountNonExpired = true;
    private final boolean credentialsNonExpired = true;
    private final boolean accountNonLocked = true;

    @Autowired
    private UserRepository userRepository;

    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        logger.info("Fetching user by email-address '{}'", email);
        final User user = userRepository.findByEmail(email);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("No user found with email-address: " + email);
        }

        logger.info("User details found! Getting authorities for authenticated user {}", user);
        return getAuthenticatedUser(user);
    }

    private UserDetails getAuthenticatedUser(final User user) {
        return new org.springframework.security.core.userdetails.User
                (user.getEmail(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
                        accountNonLocked,
                        getAuthorities(getUserRoles(user)));
    }

    private List<String> getUserRoles(final User user) {
        return user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList());
    }

}
