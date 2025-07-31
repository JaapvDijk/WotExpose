package com.learningjava.wotapi.api.model.worldoftanks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WoTPlayerResponse {
    private String nickname;
    private int account_id;
}