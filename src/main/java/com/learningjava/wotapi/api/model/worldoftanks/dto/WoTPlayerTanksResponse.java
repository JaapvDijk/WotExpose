package com.learningjava.wotapi.api.model.worldoftanks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WoTPlayerTanksResponse {
    public List<TankStatistics> data;

    @Data
    @NoArgsConstructor
    public static class TankStatistics{
        public Statistics statistics;
        public int mark_of_mastery;
        public int tank_id;
    }

    @Data
    @NoArgsConstructor
    public static class Statistics{
        public int wins;
        public int battles;
    }
}
