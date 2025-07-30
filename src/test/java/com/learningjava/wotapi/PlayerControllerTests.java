package com.learningjava.wotapi;

import com.learningjava.wotapi.api.importer.TomatoImporter;

import com.learningjava.wotapi.api.service.WargamingClient;
import com.learningjava.wotapi.api.service.WargamingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.containsString;

//TODO: Tests for endpoints:
// - Player/Info/{Id}
// - Player/Tanks/{Id}
@SpringBootTest
@AutoConfigureMockMvc
//@ImportAutoConfiguration(exclude = SecurityAutoConfiguration.class) //Required For admin, user controller tests?
public class PlayerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    private final int playerId = 537793577;
    private final String playerName = "iyouxin";
    private final int unknownId = 123456789;

    //region SEARCH ENDPOINTS
    @Test
    public void testSearch_withValidInput_returnsOk() throws Exception {
       var a = mockMvc.perform(get("/player/search")
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
                .andExpect(content().string(containsString("Region must be either 'EU', 'NA' or 'ASIA'")));
    }
    //endregion

    //region INFO ENDPOINTS
    @Test
    public void testGetInfo_withValidId_returnsOk() throws Exception {

        mockMvc.perform(get("/player/info/{id}", playerId)
                        .param("region", "EU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("account_id").value(playerId))
                .andExpect(jsonPath("nickname").value(playerName));
    }

    @Test
    public void testGetInfo_withUnknownId_returnsNotFound() throws Exception {
        //Act & Assert
        mockMvc.perform(get("/player/info/{id}", unknownId)
                        .param("region", "EU"))
                .andExpect(status().isNotFound());
    }
    //endregion
}
