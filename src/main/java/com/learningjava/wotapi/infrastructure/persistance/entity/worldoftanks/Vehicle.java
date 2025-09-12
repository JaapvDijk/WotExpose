package com.learningjava.wotapi.infrastructure.persistance.entity.worldoftanks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle", indexes = {
        @Index(name = "idx_vehicle_import_date", columnList = "import_date"),
        @Index(name = "idx_vehicle_tank_id", columnList = "tank_id"),
        @Index(name = "idx_vehicle_region", columnList = "region")
})
public class Vehicle {
    @CreationTimestamp
    @Column(name = "import_date")
    private LocalDate importDate;

    @EmbeddedId
    @Column(name = "vehicle_key")
    private VehicleKey id;

    private String name;

    @Column(name = "short_name")
    private String shortName;

    private String nation;

    @Column(name = "is_premium")
    private boolean isPremium;

    private String tag;

    private String type;

    private int tier;

    @Column(name = "is_missing_from_source")
    private boolean isMissingFromSource;

    @Embedded
    private Images images;

    @Embeddable
    @Data
    @NoArgsConstructor
    public static class Images{
        @Column(name = "small_icon")
        private String smallIcon;
        @Column(name = "contour_icon")
        private String contourIcon;
        @Column(name = "big_icon")
        private String bigIcon;
    }
}
