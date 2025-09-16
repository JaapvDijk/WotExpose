package com.learningjava.wotexpose.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotexpose.application.dto.LoginRequest;
import com.learningjava.wotexpose.application.dto.RegisterUserRequest;
import com.learningjava.wotexpose.application.service.JwtService;
import com.learningjava.wotexpose.infrastructure.persistance.TestDataSeeder;
import com.learningjava.wotexpose.integration.IntegrationTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TestDataSeeder testDbSeeder;

    @BeforeAll
    void setup() {
        testDbSeeder.init();
    }

    @Value("${test.user.pass}")
    private String userPassword;

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
        var user = testDbSeeder.getAdminUser();

        LoginRequest req = new LoginRequest();
        req.setEmail(user.getEmail());
        req.setPassword(userPassword);

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