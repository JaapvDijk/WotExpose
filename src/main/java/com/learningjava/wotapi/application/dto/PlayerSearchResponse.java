package com.learningjava.wotapi.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerSearchResponse {
        private String nickname;
        private int accountId;
}
