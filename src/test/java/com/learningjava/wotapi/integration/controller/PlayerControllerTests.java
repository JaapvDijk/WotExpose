package com.learningjava.wotapi.integration.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PlayerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    private final int playerId = 537793577;
    private final String playerName = "iyouxin";

    @Test
    public void testSearch_withValidInput_returnsOk() throws Exception {
       mockMvc.perform(get("/player/search")
                        .param("name", playerName)
                        .param("region", "EU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].nickname").value(playerName))
                .andExpect(jsonPath("[0].account_id").value(playerId));
    }

    @Test
    void testSearch_missingName_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/player/search")
                        .param("region", "EU"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Name is required")));
    }

    @Test
    void testSearch_nameTooShort_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/player/search")
                        .param("name", "AB")
                        .param("region", "EU"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Name must be at least 3 characters long")));
    }

    @Test //Region only needs to be tested for Search as other implementations of region are similar
    void testSearch_missingRegion_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/player/search")
                        .param("name", "AnyName"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Region is required")));
    }

    @Test
    void testSearch_invalidRegion_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/player/search")
                        .param("name", "AnyName")
                        .param("region", "SA")) // Invalid
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Unknown region code")));
    }

    @Test
    public void testGetInfo_withValidId_returnsOk() throws Exception {
        mockMvc.perform(get("/player/info/{id}", playerId)
                        .param("region", "EU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("accountId").value(playerId))
                .andExpect(jsonPath("nickname").value(playerName));
    }

    @Test
    public void testGetInfo_withUnknownId_returnsNotFound() throws Exception {
        int unknownId = 123456789;

        mockMvc.perform(get("/player/info/{id}", unknownId)
                        .param("region", "EU"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetTanks_withValidId_returnsOk() throws Exception {
        mockMvc.perform(get("/player/tanks/{id}", playerId)
                        .param("region", "EU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$[0].tank_id", isA(Integer.class)))
                .andExpect(jsonPath("$[0].tank_id", greaterThan(0)))
                .andExpect(jsonPath("$[0].mark_of_mastery", allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(4))))
                .andExpect(jsonPath("$[0].statistics", notNullValue()));
    }

    @Test
    public void testGetTanks_withUnknownId_returnsNotFound() throws Exception {
        int unknownId = 123456789;

        mockMvc.perform(get("/player/tanks/{id}", unknownId)
                        .param("region", "EU"))
                .andExpect(status().isNotFound());
    }
}
