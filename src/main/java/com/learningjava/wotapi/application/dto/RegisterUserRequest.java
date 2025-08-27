package com.learningjava.wotapi.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserRequest {
    @Email
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private String fullName;
}