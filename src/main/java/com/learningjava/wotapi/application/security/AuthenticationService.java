package com.learningjava.wotapi.application.security;

import com.learningjava.wotapi.application.mapper.UserMapper;
import com.learningjava.wotapi.application.dto.LoginRequest;
import com.learningjava.wotapi.application.dto.RegisterUserRequest;
import com.learningjava.wotapi.application.dto.UserResponse;
import com.learningjava.wotapi.infrastructure.model.entity.User;
import com.learningjava.wotapi.infrastructure.model.entity.Role;
import com.learningjava.wotapi.infrastructure.repo.RoleRepository;
import com.learningjava.wotapi.infrastructure.repo.UserRepository;
import com.learningjava.wotapi.shared.constant.Roles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserMapper mapper;

    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 AuthenticationManager authenticationManager,
                                 BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.mapper = userMapper;
    }

    public UserResponse signup(RegisterUserRequest input) {
        var user = new User();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        Role role = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User role not found"));

        user.setRoles(new HashSet<>(List.of(role)));

        var result = userRepository.save(user);
        return mapper.toUserResponse(result);
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