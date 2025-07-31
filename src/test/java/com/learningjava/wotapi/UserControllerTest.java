package com.learningjava.wotapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotapi.api.model.dto.UserRequest;
import com.learningjava.wotapi.api.model.entity.Role;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@ImportAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void setup() {
        testUser = new User();
        testUser.setEmail("admin@test.com");
        testUser.setFullName("Admin");
        var roles = List.of(new Role());
        testUser.setRoles(roles);
        userRepository.save(testUser);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetUserPage_returnsUserList() throws Exception {
        mockMvc.perform(get("/user/page")
                        .param("pageNr", "0")
                        .param("amount", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].email").value("admin@test.com"))
                .andExpect(jsonPath("$.content[0].fullName").value("Test Admin"));
    }

    @Test
    void testGetUserById_returnsUser() throws Exception {
        mockMvc.perform(get("/user/" + testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("admin@test.com"))
                .andExpect(jsonPath("$.fullName").value("Admin"));
    }

    @Test
    void testGetUserById_notFound() throws Exception {
        mockMvc.perform(get("/user/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateUser_success() throws Exception {
        UserRequest update = new UserRequest("Updated Name", "new@test.com");

        mockMvc.perform(put("/user/" + testUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("new@test.com"));
    }

    @Test
    void testUpdateUser_invalidEmail_returnsBadRequest() throws Exception {
        UserRequest update = new UserRequest("Name", "invalid-email");

        mockMvc.perform(put("/user/" + testUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteUser_success() throws Exception {
        mockMvc.perform(delete("/user/" + testUser.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUser_notFound() throws Exception {
        mockMvc.perform(delete("/user/99999"))
                .andExpect(status().isNotFound());
    }
}
