package com.learningjava.wotapi.api.model.dto;

import jakarta.validation.constraints.Email;

public class UserRequest {
    private String fullName;
    @Email(message = "Must be a valid email address")
    private String email;

    public UserRequest(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
