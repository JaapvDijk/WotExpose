package com.learningjava.wotapi.api.model.worldoftanks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
        private String nickname;
        private int account_id;
}