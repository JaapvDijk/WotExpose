package com.learningjava.wotapi.api.model.worldoftanks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class WoTPlayersResponse {
    public String status;
    public Meta meta;
    public List<Player> data;

    public static class Player{
        public String nickname;
        public int account_id;
    }

    public static class Meta{
        public int count;
    }
}

