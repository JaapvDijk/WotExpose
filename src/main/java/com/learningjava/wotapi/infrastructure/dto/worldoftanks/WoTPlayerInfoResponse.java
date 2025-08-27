package com.learningjava.wotapi.infrastructure.dto.worldoftanks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class WoTPlayerInfoResponse {
    @JsonProperty("client_language")
    private String clientLanguage;
    @JsonProperty("last_battle_time")
    private int lastBattleTime;
    @JsonProperty("account_id")
    private int accountId;
    @JsonProperty("created_at")
    private int createdAt;
    @JsonProperty("updated_at")
    private int updatedAt;
    @JsonProperty("private")
    private Private privateInfo;
    @JsonProperty("global_rating")
    private int globalRating;
    @JsonProperty("clan_id")
    private int clanId;
    private Statistics statistics;
    private String nickname;

    @JsonProperty("logout_at")
    private int logoutAt;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Private{
        private Restrictions restrictions;
        private int gold;
        @JsonProperty("free_xp")
        private int freeXp;
        @JsonProperty("ban_time")
        private Object banTime;
        @JsonProperty("is_bound_to_phone")
        private boolean isBoundToPhone;
        @JsonProperty("is_premium")
        private boolean isPremium;
        private int credits;
        @JsonProperty("premium_expires_at")
        private int premiumExpiresAt;
        private int bonds;
        @JsonProperty("battle_life_time")
        private int battleLifeTime;
        @JsonProperty("ban_info")
        private Object banInfo;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Restrictions{
        public Object chat_ban_time;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Statistics {
        private BaseStatistics clan;
        private ExtraStatistics all;
        @JsonProperty("regular_team")
        private ExtraStatistics regularTeam;
        @JsonProperty("trees_cut")
        private int treesCut;
        private BaseStatistics company;
        @JsonProperty("stronghold_skirmish")
        private ExtraStatistics strongholdSkirmish;
        @JsonProperty("stronghold_defense")
        private ExtraStatistics strongholdDefense;
        private ExtraStatistics historical;
        private ExtraStatistics team;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BaseStatistics {
        private int spotted;
        @JsonProperty("battles_on_stunning_vehicles")
        private int battlesOnStunningVehicles;
        @JsonProperty("track_assisted_damage")
        private int trackAssistedDamage;
        @JsonProperty("avg_damage_blocked")
        private double avgDamageBlocked;
        @JsonProperty("direct_hits_received")
        private int directHitsReceived;
        @JsonProperty("explosion_hits")
        private int explosionHits;
        @JsonProperty("piercings_received")
        private int piercingsReceived;
        private int piercings;
        private int xp;
        @JsonProperty("survived_battles")
        private int survivedBattles;
        @JsonProperty("dropped_capture_points")
        private int droppedCapturePoints;
        @JsonProperty("hits_percents")
        private int hitsPercents;
        private int draws;
        private int battles;
        @JsonProperty("damage_received")
        private int damageReceived;
        @JsonProperty("avg_damage_assisted")
        private double avgDamageAssisted;
        @JsonProperty("avg_damage_assisted_track")
        private double avgDamageAssistedTrack;
        private int frags;
        @JsonProperty("stun_number")
        private int stunNumber;
        @JsonProperty("avg_damage_assisted_radio")
        private double avgDamageAssistedRadio;
        @JsonProperty("capture_points")
        private int capturePoints;
        @JsonProperty("explosion_hits_received")
        private int explosionHitsReceived;
        @JsonProperty("stun_assisted_damage")
        private int stunAssistedDamage;
        private int hits;
        @JsonProperty("battle_avg_xp")
        private int battleAvgXp;
        private int wins;
        private int losses;
        @JsonProperty("damage_dealt")
        private int damageDealt;
        @JsonProperty("no_damage_direct_hits_received")
        private int noDamageDirectHitsReceived;
        private int shots;
        @JsonProperty("radio_assisted_damage")
        private int radioAssistedDamage;
        @JsonProperty("avg_damage_assisted_stun")
        private double avgDamageAssistedStun;
        @JsonProperty("tanking_factor")
        private double tankingFactor;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExtraStatistics extends BaseStatistics {
        @JsonProperty("max_xp")
        private int maxXp;
        @JsonProperty("max_xp_tank_id")
        private int maxXpTankId;
        @JsonProperty("max_frags")
        private int maxFrags;
        @JsonProperty("max_frags_tank_id")
        private int maxFragsTankId;
        @JsonProperty("max_damage")
        private int maxDamage;
        @JsonProperty("max_damage_tank_id")
        private int maxDamageTankId;
    }
}