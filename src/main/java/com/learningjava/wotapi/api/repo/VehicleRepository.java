package com.learningjava.wotapi.api.repo;

import com.learningjava.wotapi.api.model.worldoftanks.dto.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findById_TankIdAndId_Region(int tankId, String region);
}
