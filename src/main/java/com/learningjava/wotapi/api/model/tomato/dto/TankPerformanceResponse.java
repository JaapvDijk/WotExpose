package com.learningjava.wotapi.api.model.tomato.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TankPerformanceResponse {
    @JsonProperty("tank_id")
    private int tankId;

    private String name;
    private String nation;
    private int tier;

    @JsonProperty("class")
    private String tankClass;

    private String image;

    @JsonProperty("big_image")
    private String bigImage;

    private int battles;

    @JsonProperty("player_wn8")
    private int playerWn8;

    private double winrate;

    @JsonProperty("player_winrate")
    private double playerWinrate;

    @JsonProperty("winrate_differential")
    private double winrateDifferential;

    private int damage;

    @JsonProperty("sniper_damage")
    private int sniperDamage;

    private double frags;

    @JsonProperty("shots_fired")
    private double shotsFired;

    @JsonProperty("direct_hits")
    private double directHits;

    private double penetrations;

    @JsonProperty("hit_rate")
    private double hitRate;

    @JsonProperty("pen_rate")
    private double penRate;

    @JsonProperty("spotting_assist")
    private int spottingAssist;

    @JsonProperty("tracking_assist")
    private int trackingAssist;

    private double spots;

    @JsonProperty("damage_blocked")
    private int damageBlocked;

    @JsonProperty("damage_received")
    private int damageReceived;

    @JsonProperty("potential_damage_received")
    private int potentialDamageReceived;

    @JsonProperty("base_capture_points")
    private double baseCapturePoints;

    @JsonProperty("base_defense_points")
    private double baseDefensePoints;

    @JsonProperty("life_time")
    private int lifeTime;

    private double survival;

    @JsonProperty("distance_traveled")
    private int distanceTraveled;

    @JsonProperty("base_xp")
    private int baseXp;

    private int wn8;

    @JsonProperty("is_prem")
    private boolean isPrem;
}
