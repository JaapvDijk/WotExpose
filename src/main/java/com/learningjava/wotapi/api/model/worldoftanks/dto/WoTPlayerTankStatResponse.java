package com.learningjava.wotapi.api.model.worldoftanks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WoTPlayerTankStatResponse {
    public MaxStats clan;
    @JsonProperty("stronghold_skirmish")
    public StrongholdSkirmish strongholdSkirmish;
    @JsonProperty("regular_team")
    public MaxStats regularTeam;
    @JsonProperty("account_id")
    public int accountId;
    @JsonProperty("max_xp")
    public int maxXp;
    public BaseStatistics company;
    public ExtraStats all;
    @JsonProperty("stronghold_defense")
    public StrongholdDefense strongholdDefense;
    @JsonProperty("max_frags")
    public int maxFrags;
    public MaxStats team;
    public ExtraStats globalmap;
    public Object frags;
    @JsonProperty("mark_of_mastery")
    public int markOfMastery;
    @JsonProperty("in_garage")
    public Object inGarage;
    @JsonProperty("tank_id")
    public int tankId;

    public static class BaseStatistics {
        public int spotted;
        @JsonProperty("battles_on_stunning_vehicles")
        public int battlesOnStunningVehicles;
        @JsonProperty("track_assisted_damage")
        public int trackAssistedDamage;
        public int xp;
        @JsonProperty("survived_battles")
        public int survivedBattles;
        @JsonProperty("dropped_capture_points")
        public int droppedCapturePoints;
        @JsonProperty("hits_percents")
        public int hitsPercents;
        public int draws;
        public int battles;
        @JsonProperty("damage_received")
        public int damageReceived;
        public int frags;
        @JsonProperty("stun_number")
        public int stunNumber;
        @JsonProperty("capture_points")
        public int capturePoints;
        @JsonProperty("stun_assisted_damage")
        public int stunAssistedDamage;
        public int hits;
        @JsonProperty("battle_avg_xp")
        public int battleAvgXp;
        public int wins;
        public int losses;
        @JsonProperty("damage_dealt")
        public int damageDealt;
        public int shots;
        @JsonProperty("radio_assisted_damage")
        public int radioAssistedDamage;
    }

    public static class ExtraStats extends BaseStatistics {
        @JsonProperty("avg_damage_blocked")
        public double avgDamageBlocked;
        @JsonProperty("avg_damage_assisted")
        public double avgDamageAssisted;
        @JsonProperty("avg_damage_assisted_track")
        public double avgDamageAssistedTrack;
        @JsonProperty("avg_damage_assisted_radio")
        public double avgDamageAssistedRadio;
        @JsonProperty("avg_damage_assisted_stun")
        public double avgDamageAssistedStun;
        @JsonProperty("tanking_factor")
        public double tankingFactor;
        @JsonProperty("direct_hits_received")
        public int directHitsReceived;
        @JsonProperty("explosion_hits")
        public int explosionHits;
        @JsonProperty("piercings_received")
        public int piercingsReceived;
        public int piercings;
        @JsonProperty("no_damage_direct_hits_received")
        public int noDamageDirectHitsReceived;
        @JsonProperty("explosion_hits_received")
        public int explosionHitsReceived;
    }

    public static class MaxStats extends BaseStatistics {
        @JsonProperty("max_xp")
        public int maxXp;
        @JsonProperty("max_damage")
        public int maxDamage;
        @JsonProperty("max_frags")
        public int maxFrags;
    }

    public static class StrongholdDefense extends BaseStatistics {
        @JsonProperty("max_xp")
        public int maxXp;
        @JsonProperty("direct_hits_received")
        public int directHitsReceived;
        @JsonProperty("explosion_hits")
        public int explosionHits;
        @JsonProperty("piercings_received")
        public int piercingsReceived;
        public int piercings;
        @JsonProperty("max_damage")
        public int maxDamage;
        @JsonProperty("no_damage_direct_hits_received")
        public int noDamageDirectHitsReceived;
        @JsonProperty("max_frags")
        public int maxFrags;
        @JsonProperty("tanking_factor")
        public double tankingFactor;
        @JsonProperty("explosion_hits_received")
        public int explosionHitsReceived;
    }

    public static class StrongholdSkirmish extends BaseStatistics {
        @JsonProperty("max_xp")
        public int maxXp;
        @JsonProperty("direct_hits_received")
        public int directHitsReceived;
        @JsonProperty("explosion_hits")
        public int explosionHits;
        @JsonProperty("piercings_received")
        public int piercingsReceived;
        public int piercings;
        @JsonProperty("max_damage")
        public int maxDamage;
        @JsonProperty("no_damage_direct_hits_received")
        public int noDamageDirectHitsReceived;
        @JsonProperty("max_frags")
        public int maxFrags;
        @JsonProperty("explosion_hits_received")
        public int explosionHitsReceived;
        @JsonProperty("tanking_factor")
        public double tankingFactor;
    }
}