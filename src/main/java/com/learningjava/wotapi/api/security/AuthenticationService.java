package com.learningjava.wotapi.api.security;

import com.learningjava.wotapi.api.model.dto.LoginRequest;
import com.learningjava.wotapi.api.model.dto.RegisterUserRequest;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.model.entity.Role;
import com.learningjava.wotapi.api.repo.RoleRepository;
import com.learningjava.wotapi.api.repo.UserRepository;
import com.learningjava.wotapi.api.contant.Roles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 AuthenticationManager authenticationManager,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserRequest input) {
        var user = new User();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        Role role = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User role not found"));

        user.setRoles(List.of(role));

        return userRepository.save(user);
    }

    public User authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()));

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}