package com.learningjava.wotapi.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class PlayerInfoResponse {
    private String clientLanguage;
    private int lastBattleTime;
    private int accountId;
    private int createdAt;
    private int updatedAt;
    private Private privateInfo;
    private int globalRating;
    private int clanId;
    private Statistics statistics;
    private String nickname;
    private int logoutAt;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Private{
        private int gold;
        private int freeXp;
        private Object banTime;
        private boolean isBoundToPhone;
        private boolean isPremium;
        private int credits;
        private int premiumExpiresAt;
        private int bonds;
        private int battleLifeTime;
        private Object banInfo;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Statistics {
        private BaseStatistics clan;
        private ExtraStatistics all;
        private ExtraStatistics regularTeam;
        private int treesCut;
        private BaseStatistics company;
        private ExtraStatistics strongholdSkirmish;
        private ExtraStatistics strongholdDefense;
        private ExtraStatistics historical;
        private ExtraStatistics team;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BaseStatistics {
        private int spotted;
        private int battlesOnStunningVehicles;
        private int trackAssistedDamage;
        private double avgDamageBlocked;
        private int directHitsReceived;
        private int explosionHits;
        private int piercingsReceived;
        private int piercings;
        private int xp;
        private int survivedBattles;
        private int droppedCapturePoints;
        private int hitsPercents;
        private int draws;
        private int battles;
        private int damageReceived;
        private double avgDamageAssisted;
        private double avgDamageAssistedTrack;
        private int frags;
        private int stunNumber;
        private double avgDamageAssistedRadio;
        private int capturePoints;
        private int explosionHitsReceived;
        private int stunAssistedDamage;
        private int hits;
        private int battleAvgXp;
        private int wins;
        private int losses;
        private int damageDealt;
        private int noDamageDirectHitsReceived;
        private int shots;
        private int radioAssistedDamage;
        private double avgDamageAssistedStun;
        private double tankingFactor;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExtraStatistics extends BaseStatistics {
        private int maxXp;
        private int maxXpTankId;
        private int maxFrags;
        private int maxFragsTankId;
        private int maxDamage;
        private int maxDamageTankId;
    }
}
