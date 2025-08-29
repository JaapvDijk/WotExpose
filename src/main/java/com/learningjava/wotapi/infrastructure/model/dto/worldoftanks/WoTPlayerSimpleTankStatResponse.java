package com.learningjava.wotapi.infrastructure.model.dto.worldoftanks;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WoTPlayerSimpleTankStatResponse {
    public Statistics statistics;
    public int mark_of_mastery;
    public int tank_id;

    @Data
    @NoArgsConstructor
    @Schema(name = "TankStatistics")
    public static class Statistics{
        public int wins;
        public int battles;
    }
}
