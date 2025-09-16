package com.learningjava.wotexpose.infrastructure.client.wargaming.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.shared.BaseStatistics;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.shared.ExtraStatistics;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;

@Generated("")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WoTPlayerTankStatResponse {
    private MaxStats clan;
    @JsonProperty("stronghold_skirmish")
    private StrongholdSkirmish strongholdSkirmish;
    @JsonProperty("regular_team")
    private MaxStats regularTeam;
    @JsonProperty("account_id")
    private int accountId;
    @JsonProperty("max_xp")
    private int maxXp;
    private BaseStatistics company;
    private ExtraStatistics all;
    @JsonProperty("stronghold_defense")
    private StrongholdDefense strongholdDefense;
    @JsonProperty("max_frags")
    private int maxFrags;
    private MaxStats team;
    private ExtraStatistics globalmap;
    private Object frags;
    @JsonProperty("mark_of_mastery")
    private int markOfMastery;
    @JsonProperty("in_garage")
    private Object inGarage;
    @JsonProperty("tank_id")
    private int tankId;

    @Data
    @NoArgsConstructor
    private static class MaxStats extends BaseStatistics {
        @JsonProperty("max_xp")
        private int maxXp;
        @JsonProperty("max_damage")
        private int maxDamage;
        @JsonProperty("max_frags")
        private int maxFrags;
    }

    @Data
    @NoArgsConstructor
    public static class StrongholdDefense extends BaseStatistics {
        @JsonProperty("max_xp")
        private int maxXp;
        @JsonProperty("direct_hits_received")
        private int directHitsReceived;
        @JsonProperty("explosion_hits")
        private int explosionHits;
        @JsonProperty("piercings_received")
        private int piercingsReceived;
        private int piercings;
        @JsonProperty("max_damage")
        private int maxDamage;
        @JsonProperty("no_damage_direct_hits_received")
        private int noDamageDirectHitsReceived;
        @JsonProperty("max_frags")
        private int maxFrags;
        @JsonProperty("tanking_factor")
        private double tankingFactor;
        @JsonProperty("explosion_hits_received")
        private int explosionHitsReceived;
    }

    @Data
    @NoArgsConstructor
    public static class StrongholdSkirmish extends BaseStatistics {
        @JsonProperty("max_xp")
        private int maxXp;
        @JsonProperty("direct_hits_received")
        private int directHitsReceived;
        @JsonProperty("explosion_hits")
        private int explosionHits;
        @JsonProperty("piercings_received")
        private int piercingsReceived;
        private int piercings;
        @JsonProperty("max_damage")
        private int maxDamage;
        @JsonProperty("no_damage_direct_hits_received")
        private int noDamageDirectHitsReceived;
        @JsonProperty("max_frags")
        private int maxFrags;
        @JsonProperty("explosion_hits_received")
        private int explosionHitsReceived;
        @JsonProperty("tanking_factor")
        private double tankingFactor;
    }
}