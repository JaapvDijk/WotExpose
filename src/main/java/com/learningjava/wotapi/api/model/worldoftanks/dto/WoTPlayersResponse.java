package com.learningjava.wotapi.api.model.worldoftanks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class WoTPlayersResponse {
    private String status;
    private Meta meta;
    private List<Player> data;

    @Data
    @NoArgsConstructor
    public static class Player {
        private String nickname;
        private int account_id;
    }

    @Data
    @NoArgsConstructor
    public static class Meta {
        private int count;
    }
}