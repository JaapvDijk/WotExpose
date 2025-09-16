package com.learningjava.wotexpose.application.dto;

import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.shared.BaseStatistics;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.shared.ExtraStatistics;
import lombok.Data;
import lombok.NoArgsConstructor;

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
