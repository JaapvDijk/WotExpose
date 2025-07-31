package com.learningjava.wotapi.api.model.worldoftanks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TankStatisticsResponse {
    public Statistics statistics;
    public int mark_of_mastery;
    public int tank_id;

    @Data
    @NoArgsConstructor
    public static class Statistics{
        public int wins;
        public int battles;
    }
}
