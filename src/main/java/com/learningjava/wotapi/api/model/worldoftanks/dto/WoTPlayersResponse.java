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

        public PlayerResponse toPlayerResponse() {
            return new PlayerResponse(account_id ,nickname);
        }
    }

    public static class Meta{
        public int count;
    }

    public List<PlayerResponse> toPlayersResponse() {
        return Optional.ofNullable(data) //Check for null here, Jackson may overwrite the
                .orElseGet(ArrayList::new) //prop with null in case of a {"data": null} response
                .stream()
                .map(Player::toPlayerResponse)
                .collect(Collectors.toList());
    }
}

