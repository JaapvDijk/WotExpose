package com.learningjava.wotapi.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerSearchResponse {
        private String nickname;
        @JsonProperty("account_id")
        private int accountId;
}
