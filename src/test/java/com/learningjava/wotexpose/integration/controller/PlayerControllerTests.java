package com.learningjava.wotexpose.integration.controller;

import com.learningjava.wotexpose.integration.IntegrationTestBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

@Tag("callerIpMustBeStatic")
class PlayerControllerTests extends IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;

    private final int playerId = 537793577;
    private final String playerName = "iyouxin";

    @Test
    void testSearch_withValidInput_returnsOk() throws Exception {
       mockMvc.perform(get("/player/search")
                        .param("name", playerName)
                        .param("region", "EU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].nickname").value(playerName))
                .andExpect(jsonPath("[0].accountId").value(playerId));
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
    void testGetInfo_withValidId_returnsOk() throws Exception {
        mockMvc.perform(get("/player/info/{id}", playerId)
                        .param("region", "EU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("accountId").value(playerId))
                .andExpect(jsonPath("nickname").value(playerName));
    }

    @Test
    void testGetInfo_withUnknownId_returnsNotFound() throws Exception {
        int unknownId = 123456789;

        mockMvc.perform(get("/player/info/{id}", unknownId)
                        .param("region", "EU"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTanks_withValidId_returnsOk() throws Exception {
        mockMvc.perform(get("/player/tanks/{id}", playerId)
                        .param("region", "EU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$.data", not(empty())))
                .andExpect(jsonPath("$.data[0].tankId", greaterThan(0)))
                .andExpect(jsonPath("$.data[0].markOfMastery", allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(4))));

        //TODO: expand further for stas props, now only raw data is tested
    }

    @Test
    void testGetTanks_withUnknownId_returnsNotFound() throws Exception {
        int unknownId = 123456789;

        mockMvc.perform(get("/player/tanks/{id}", unknownId)
                        .param("region", "EU"))
                .andExpect(status().isNotFound());
    }
}
