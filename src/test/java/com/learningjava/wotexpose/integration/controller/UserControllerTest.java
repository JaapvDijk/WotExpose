package com.learningjava.wotexpose.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotexpose.application.dto.UserRequest;
import com.learningjava.wotexpose.infrastructure.persistance.TestDataSeeder;
import com.learningjava.wotexpose.integration.IntegrationTestBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WithMockUser(username = "admin", roles = {"ADMIN"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest extends IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestDataSeeder testDataSeeder;

    @BeforeAll
    void setup() {
        testDataSeeder.init();
    }

    @Test
    void testGetUserPage_returnsUserList() throws Exception {
        mockMvc.perform(get("/user/page")
                        .param("pageNr", "0")
                        .param("amount", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].email").value("admin@api.nl"))
                .andExpect(jsonPath("$.content[0].fullName").value("admin"))
                .andExpect(jsonPath("$.content[1].email").value("user@api.nl"))
                .andExpect(jsonPath("$.content[1].fullName").value("user"));
    }

    @Test
    void testGetUserById_returnsUser() throws Exception {
        int id = testDataSeeder.getAdminUser().getId();

        mockMvc.perform(get("/user/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("admin@api.nl"))
                .andExpect(jsonPath("$.fullName").value("admin"));
    }

    @Test
    void testGetUserById_notFound() throws Exception {
        mockMvc.perform(get("/user/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateUser_success() throws Exception {
        int id = testDataSeeder.getAdminUser().getId();
        UserRequest update = new UserRequest("Updated Name", "new@test.com");

        mockMvc.perform(put("/user/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("new@test.com"));
    }

    @Test
    void testUpdateUser_invalidEmail_returnsBadRequest() throws Exception {
        int id = testDataSeeder.getNormalUser().getId();
        UserRequest update = new UserRequest("Name", "invalid-email");

        mockMvc.perform(put("/user/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteUser_success() throws Exception {
        int id = testDataSeeder.getNormalUser().getId();

        mockMvc.perform(delete("/user/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUser_notFound() throws Exception {
        mockMvc.perform(delete("/user/99999"))
                .andExpect(status().isNotFound());
    }
}
