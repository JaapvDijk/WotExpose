package com.learningjava.wotapi.api.model.dto;

import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerTankStatResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlayerTankStatsResponse {
    private List<PlayerTankStatResponse> data;

    private int totalBattlesAll;

    private int totalBattlesLight;
    private int totalBattlesMedium;
    private int totalBattlesHeavy;
    private int totalBattlesSPG;
}