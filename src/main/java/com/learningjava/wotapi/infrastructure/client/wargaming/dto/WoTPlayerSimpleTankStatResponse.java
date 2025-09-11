package com.learningjava.wotapi.infrastructure.client.wargaming.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
