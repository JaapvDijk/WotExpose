package com.learningjava.wotexpose.infrastructure.persistance.entity.worldoftanks;

import com.learningjava.wotexpose.shared.constant.RegionType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VehicleKey implements Serializable {

    @Column(name = "tank_id")
    private int tankId;

    @Enumerated(EnumType.STRING)
    @Column(name = "region", nullable = false, length = 10)
    private RegionType region = RegionType.EU;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleKey that)) return false;
        return tankId == that.tankId && Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tankId, region);
    }
}