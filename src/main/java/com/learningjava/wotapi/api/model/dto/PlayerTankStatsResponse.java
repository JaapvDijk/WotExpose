package com.learningjava.wotapi.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerTankStatResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlayerTankStatsResponse {
    private List<WoTPlayerTankStatResponse> data;

    private int totalBattlesAll;
}