package com.learningjava.wotapi.api.model.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    @Email
    private String email;
    private String password;
}