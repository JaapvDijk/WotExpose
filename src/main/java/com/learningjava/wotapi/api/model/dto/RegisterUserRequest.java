package com.learningjava.wotapi.api.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class RegisterUserRequest {
    @Email
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private String fullName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}