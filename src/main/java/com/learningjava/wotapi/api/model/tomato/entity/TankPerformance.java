package com.learningjava.wotapi.api.model.tomato.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tank_performance", indexes = {
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_import_date", columnList = "import_date"),
        @Index(name = "idx_region", columnList = "region")
})
public class TankPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    @Column(name = "import_date")
    private LocalDate importDate;
    private String region;
    private int tankId;
    private String name;
    private String nation;
    private int tier;
    private String tankClass;
    private String image;
    private String bigImage;
    private int battles;
    private int playerWn8;
    private double winrate;
    private double playerWinrate;
    private double winrateDifferential;
    private int damage;
    private int sniperDamage;
    private double frags;
    private double shotsFired;
    private double directHits;
    private double penetrations;
    private double hitRate;
    private double penRate;
    private int spottingAssist;
    private int trackingAssist;
    private double spots;
    private int damageBlocked;
    private int damageReceived;
    private int potentialDamageReceived;
    private double baseCapturePoints;
    private double baseDefensePoints;
    private int lifeTime;
    private double survival;
    private int distanceTraveled;
    private int baseXp;
    private int wn8;
    private boolean isPrem;
}