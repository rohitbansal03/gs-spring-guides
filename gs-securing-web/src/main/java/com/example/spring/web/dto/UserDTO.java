package com.example.spring.web.dto;

import com.example.spring.annotation.Email;
import com.example.spring.enums.RoleType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotBlank(message = "First name cannot be left blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be left blank")
    private String lastName;

    @NotBlank(message = "Password cannot be left blank")
    private String password;

    @NotBlank(message = "Confirm Password cannot be left blank")
    private String matchingPassword;

    @NotBlank(message = "Email address cannot be left blank")
    @Email
    private String email;

    @NotNull(message = "Role cannot be left blank")
    private RoleType role;

}
