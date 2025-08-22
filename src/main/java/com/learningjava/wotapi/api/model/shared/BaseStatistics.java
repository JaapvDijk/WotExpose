package com.learningjava.wotapi.api.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseStatistics {
    private int spotted;
    @JsonProperty("battles_on_stunning_vehicles")
    private int battlesOnStunningVehicles;
    @JsonProperty("track_assisted_damage")
    private int trackAssistedDamage;
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
    private int frags;
    @JsonProperty("stun_number")
    private int stunNumber;
    @JsonProperty("capture_points")
    private int capturePoints;
    @JsonProperty("stun_assisted_damage")
    private int stunAssistedDamage;
    private int hits;
    @JsonProperty("battle_avg_xp")
    private int battleAvgXp;
    private int wins;
    private int losses;
    @JsonProperty("damage_dealt")
    private int damageDealt;
    private int shots;
    @JsonProperty("radio_assisted_damage")
    private int radioAssistedDamage;
}