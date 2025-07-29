package com.learningjava.wotapi.api.model.tomato.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(
        name = "tank_performance",
        indexes = {
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_import_date", columnList = "import_date"),
                @Index(name = "idx_region", columnList = "region")
        }
)
public class TankPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(name = "import_date")
    private LocalDate importDate;

    private String region;

    private int tank_id;
    private String name;
    private String nation;
    private int tier;
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