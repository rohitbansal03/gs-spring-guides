package com.example.spring.web.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserPrincipal extends User {

    private final String name;

    public UserPrincipal(final String username, final String password,
                         final Collection<? extends GrantedAuthority> authorities, String name) {
        super(username, password, authorities);
        this.name = name;
    }

    public UserPrincipal(final String username, final String password, final boolean enabled,
                         final boolean accountNonExpired, final boolean credentialsNonExpired,
                         final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities,
                         final String name) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
