package com.learningjava.wotapi.infrastructure.client.wargaming.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WoTPlayerResponse {
    private String nickname;
    @JsonProperty("account_id")
    private int accountId;
}