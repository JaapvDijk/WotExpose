package com.learningjava.wotapi.application.dto;

import com.learningjava.wotapi.infrastructure.client.wargaming.dto.shared.BaseStatistics;
import com.learningjava.wotapi.infrastructure.client.wargaming.dto.shared.ExtraStatistics;
import lombok.Data;
import lombok.NoArgsConstructor;

//Commented props may be needed in the future, for now only 'All' is needed

@Data
@NoArgsConstructor
public class PlayerTankStatResponse {
    private int accountId;
    private int maxXp;
    private BaseStatistics company;
    private ExtraStatistics all;
    private ExtraStatistics globalmap;
    private Object frags;
    private int markOfMastery;
    private Object inGarage;
    private int tankId;
}
