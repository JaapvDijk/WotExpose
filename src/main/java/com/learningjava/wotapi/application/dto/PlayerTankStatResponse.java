package com.learningjava.wotapi.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningjava.wotapi.infrastructure.model.shared.BaseStatistics;
import com.learningjava.wotapi.infrastructure.model.shared.ExtraStatistics;
import lombok.Data;
import lombok.NoArgsConstructor;

//Commented props may be needed in the future, for now only 'All' is needed

@Data
@NoArgsConstructor
public class PlayerTankStatResponse {
//    private WoTPlayerTankStatResponse.StrongholdSkirmish strongholdSkirmish;
//    @JsonProperty("regular_team")
//    private MaxStats regularTeam;
    @JsonProperty("account_id")
    private int accountId;
    @JsonProperty("max_xp")
    private int maxXp;
    private BaseStatistics company;
    private ExtraStatistics all;
//    @JsonProperty("stronghold_defense")
//    private StrongholdDefense strongholdDefense;
//    @JsonProperty("max_frags")
//    private int maxFrags;
//    private MaxStats team;
    private ExtraStatistics globalmap;
    private Object frags;
    @JsonProperty("mark_of_mastery")
    private int markOfMastery;
    @JsonProperty("in_garage")
    private Object inGarage;
    @JsonProperty("tank_id")
    private int tankId;
}
