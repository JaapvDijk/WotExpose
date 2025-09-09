package com.learningjava.wotapi.infrastructure.client.wargaming.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtraStatistics extends BaseStatistics {
    @JsonProperty("avg_damage_blocked")
    private double avgDamageBlocked;
    @JsonProperty("avg_damage_assisted")
    private double avgDamageAssisted;
    @JsonProperty("avg_damage_assisted_track")
    private double avgDamageAssistedTrack;
    @JsonProperty("avg_damage_assisted_radio")
    private double avgDamageAssistedRadio;
    @JsonProperty("avg_damage_assisted_stun")
    private double avgDamageAssistedStun;
    @JsonProperty("tanking_factor")
    private double tankingFactor;
    @JsonProperty("direct_hits_received")
    private int directHitsReceived;
    @JsonProperty("explosion_hits")
    private int explosionHits;
    @JsonProperty("piercings_received")
    private int piercingsReceived;
    private int piercings;
    @JsonProperty("no_damage_direct_hits_received")
    private int noDamageDirectHitsReceived;
    @JsonProperty("explosion_hits_received")
    private int explosionHitsReceived;
}