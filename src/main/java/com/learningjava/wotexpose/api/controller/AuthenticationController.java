package com.learningjava.wotexpose.api.controller;

import com.learningjava.wotexpose.application.dto.LoginResponse;
import com.learningjava.wotexpose.application.dto.LoginRequest;
import com.learningjava.wotexpose.application.dto.RegisterUserRequest;
import com.learningjava.wotexpose.application.dto.UserResponse;
import com.learningjava.wotexpose.application.security.AuthenticationService;
import com.learningjava.wotexpose.application.service.JwtService;
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
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
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