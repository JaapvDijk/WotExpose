package com.learningjava.wotapi.api.model.tomato.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TomatoTankPerformanceResponse {
            private List<TankPerformance> data;

            @Getter
            @Setter
            @NoArgsConstructor
            public static class Meta {
                private String status;
                private Date updated;
            }

            @Getter
            @Setter
            @NoArgsConstructor
            public static class TankPerformance {
                private int tank_id;
                private String name;
                private String nation;
                private int tier;

                @JsonProperty("class")
                private String tank_class;

                private String image;
                private String big_image;
                private int battles;
                private int player_wn8;
                private double winrate;
                private double player_winrate;
                private double winrate_differential;
                private int damage;
                private int sniper_damage;
                private double frags;
                private double shots_fired;
                private double direct_hits;
                private double penetrations;
                private double hit_rate;
                private double pen_rate;
                private int spotting_assist;
                private int tracking_assist;
                private double spots;
                private int damage_blocked;
                private int damage_received;
                private int potential_damage_received;
                private double base_capture_points;
                private double base_defense_points;
                private int life_time;
                private double survival;
                private int distance_traveled;
                private int base_xp;
                private int wn8;
                private boolean isPrem;
            }
        }