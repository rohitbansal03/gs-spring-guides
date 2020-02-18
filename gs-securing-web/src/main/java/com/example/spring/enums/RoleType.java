package com.example.spring.enums;

import java.util.Optional;

public enum RoleType {

    ROLE_USER,

    ROLE_ADMIN;

    public static Optional<RoleType> asRoleType(String displayValue) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.name().equalsIgnoreCase(displayValue))
                return Optional.of(roleType);
        }
        return Optional.empty();
    }

}
