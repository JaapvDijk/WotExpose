package com.learningjava.wotexpose.infrastructure.client.wargaming.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;

@Generated("")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WoTPlayerSimpleTankStatResponse {
    private Statistics statistics;
    private int mark_of_mastery;
    private int tank_id;

    @Data
    @NoArgsConstructor
    @Schema(name = "TankStatistics")
    public static class Statistics{
        private int wins;
        private int battles;
    }
}
