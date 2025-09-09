package com.learningjava.wotapi.infrastructure.persistance.entity.tomato;

import com.learningjava.wotapi.shared.constant.RegionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tank_performance", indexes = {
        @Index(name = "idx_tank_performance_name", columnList = "name"),
        @Index(name = "idx_tank_performance_import_date", columnList = "import_date"),
        @Index(name = "idx_tank_performance_region", columnList = "region")
})
public class TankPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(name = "import_date")
    private LocalDate importDate;

    private RegionType region;
    private Date updated;

    @Column(name = "tank_id")
    private int tankId;

    private String name;
    private String nation;
    private int tier;

    @Column(name = "tank_class")
    private String tankClass;

    private String image;

    @Column(name = "big_image")
    private String bigImage;

    private int battles;

    @Column(name = "player_wn8")
    private int playerWn8;

    private double winrate;

    @Column(name = "player_winrate")
    private double playerWinrate;

    @Column(name = "winrate_differential")
    private double winrateDifferential;

    private int damage;

    @Column(name = "sniper_damage")
    private int sniperDamage;

    private double frags;

    @Column(name = "shots_fired")
    private double shotsFired;

    @Column(name = "direct_hits")
    private double directHits;

    private double penetrations;

    @Column(name = "hit_rate")
    private double hitRate;

    @Column(name = "pen_rate")
    private double penRate;

    @Column(name = "spotting_assist")
    private int spottingAssist;

    @Column(name = "tracking_assist")
    private int trackingAssist;

    private double spots;

    @Column(name = "damage_blocked")
    private int damageBlocked;

    @Column(name = "damage_received")
    private int damageReceived;

    @Column(name = "potential_damage_received")
    private int potentialDamageReceived;

    @Column(name = "base_capture_points")
    private double baseCapturePoints;

    @Column(name = "base_defense_points")
    private double baseDefensePoints;

    @Column(name = "life_time")
    private int lifeTime;

    private double survival;

    @Column(name = "distance_traveled")
    private int distanceTraveled;

    @Column(name = "base_xp")
    private int baseXp;

    private int wn8;

    @Column(name = "is_prem")
    private boolean isPrem;
}