package com.learningjava.wotapi.infrastructure.model.entity.worldoftanks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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

    private String region;

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