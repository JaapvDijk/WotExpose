package com.learningjava.wotapi.api.model.tomato.dto;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();

Root root = om.readValue(myJsonString, Root.class); */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TomatoTankPerformanceResponse {
    public PageProps pageProps;
    public boolean __N_SSG;

    public static class PageProps{
        public Data data;
        public Object overallData;
        public String initialServer;
        public String mode;

        public static class Data {
            public Meta meta;
            public ArrayList<TankPerformance> data;

            public static class Meta{
                public String status;
                public Date updated;
            }

            public static class TankPerformance {
                public int tank_id;
                public String name;
                public String nation;
                public int tier;
                @JsonProperty("class")
                public String class_name;
                public String image;
                public String big_image;
                public int battles;
                public int player_wn8;
                public double winrate;
                public double player_winrate;
                public double winrate_differential;
                public int damage;
                public int sniper_damage;
                public double frags;
                public double shots_fired;
                public double direct_hits;
                public double penetrations;
                public double hit_rate;
                public double pen_rate;
                public int spotting_assist;
                public int tracking_assist;
                public double spots;
                public int damage_blocked;
                public int damage_received;
                public int potential_damage_received;
                public double base_capture_points;
                public double base_defense_points;
                public int life_time;
                public double survival;
                public int distance_traveled;
                public int base_xp;
                public int wn8;
                public boolean isPrem;
            }
        }
    }
}