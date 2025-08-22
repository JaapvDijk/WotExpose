package com.learningjava.wotapi.api.model.worldoftanks.dto.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicle", indexes = {
        @Index(name = "idx_vehicle_import_date", columnList = "import_date"),
        @Index(name = "idx_vehicle_region", columnList = "region"),
        @Index(name = "idx_vehicle_tank_id", columnList = "region")
})
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(name = "import_date")
    private LocalDate importDate;

    @Column(name = "tank_id")
    private int tankId;

    private String region;

    private String name;

    @Column(name = "short_name")
    private String shortName;

    private String nation;

    @Column(name = "is_premium")
    private boolean isPremium;

    private String tag;

    private String type;

    private int tier;
}
