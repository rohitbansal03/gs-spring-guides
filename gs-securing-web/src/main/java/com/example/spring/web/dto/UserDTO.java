package com.example.spring.web.dto;

import com.example.spring.annotation.Email;
import com.example.spring.annotation.PasswordConstraint;
import com.example.spring.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@PasswordConstraint(message = "Passwords should be matching")
public class UserDTO {

    @NotBlank(message = "First name cannot be left blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be left blank")
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password cannot be left blank")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Confirm Password cannot be left blank")
    private String matchingPassword;

    @NotBlank(message = "Email address cannot be left blank")
    @Email
    private String email;

    @NotNull(message = "Role cannot be left blank")
    private RoleType role;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public String getMatchingPassword() {
        return matchingPassword;
    }
}
