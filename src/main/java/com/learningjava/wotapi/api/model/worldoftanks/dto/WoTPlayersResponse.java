package com.learningjava.wotapi.api.model.worldoftanks.dto;

import java.util.List;

public class WoTPlayersResponse {
    public List<PlayerSummary> players;

    public static class PlayerSummary {
        public int id;
        public String name;

        public PlayerSummary(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}