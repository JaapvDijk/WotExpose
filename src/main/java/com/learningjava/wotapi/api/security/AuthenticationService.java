package com.learningjava.wotapi.api.security;

import com.learningjava.wotapi.api.mapper.UserMapper;
import com.learningjava.wotapi.api.model.dto.LoginRequest;
import com.learningjava.wotapi.api.model.dto.RegisterUserRequest;
import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.model.entity.Role;
import com.learningjava.wotapi.api.repo.RoleRepository;
import com.learningjava.wotapi.api.repo.UserRepository;
import com.learningjava.wotapi.api.constant.Roles;
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
        return mapper.toDto(result);
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