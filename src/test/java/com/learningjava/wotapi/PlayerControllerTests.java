package com.learningjava.wotapi;

import com.learningjava.wotapi.api.model.worldoftanks.dto.PlayerResponse;
import com.learningjava.wotapi.api.service.PlayerService;
import com.learningjava.wotapi.api.importer.TomatoImporter;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.containsString;

//TODO: Tests for endpoints:
// - Player/Info/{Id}
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlayerService playerService;

    @MockitoBean
    private TomatoImporter tomatoImporter;

    @Test
    public void testSearch_withValidInput_returnsOk() throws Exception {
        // Arrange
        PlayerResponse mockPlayer = new PlayerResponse("QuickBaby", 518017681);
        Mockito.when(playerService.getPlayers("QuickBaby"))
                .thenReturn(List.of(mockPlayer));

        // Act & Assert
        mockMvc.perform(get("/player/search")
                        .param("name", "QuickBaby")
                        .param("region", "EU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nickname").value("QuickBaby"))
                .andExpect(jsonPath("$[0].account_id").value(518017681));
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
}
