package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.api.model.dto.LoginResponse;
import com.learningjava.wotapi.api.model.dto.LoginRequest;
import com.learningjava.wotapi.api.model.dto.RegisterUserRequest;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.security.AuthenticationService;
import com.learningjava.wotapi.api.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService service;

    public AuthenticationController(JwtService jwtService, AuthenticationService service) {
        this.jwtService = jwtService;
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        var result = service.signup(registerUserRequest);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        var authenticatedUser = service.authenticate(loginRequest);

        var jwtToken = jwtService.generateToken(authenticatedUser);

        var result = new LoginResponse();
        result.setToken(jwtToken);
        result.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(result);
    }

    //TODO: refresh token

    //TODO: password reset
}