package com.learningjava.wotapi.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotapi.infrastructure.DbSeeder;
import com.learningjava.wotapi.application.dto.LoginRequest;
import com.learningjava.wotapi.application.dto.RegisterUserRequest;
import com.learningjava.wotapi.application.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DbSeeder dbSeeder;

    @BeforeEach
    void setup() {
        dbSeeder.init();
    }

    @Test
    void testSignupSuccess() throws Exception {
        var req = new RegisterUserRequest();
        req.setEmail("new@user.com");
        req.setPassword("SomePassword");
        req.setFullName("new test user");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("new@user.com"))
                .andExpect(jsonPath("$.fullName").value("new test user"));
    }

    @Test
    void testLoginSuccess() throws Exception {
        var user = dbSeeder.getNormalUser();

        LoginRequest req = new LoginRequest();
        req.setEmail(user.getEmail());
        req.setPassword(dbSeeder.getNormalUserPassword());

        String token = objectMapper.readTree(
                mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(req)))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
        ).get("token").asText();

        Assertions.assertTrue(jwtService.isTokenValid(token, user.getUsername()));
    }
}