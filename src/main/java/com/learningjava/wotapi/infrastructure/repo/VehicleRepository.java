package com.learningjava.wotapi.infrastructure.repo;

import com.learningjava.wotapi.infrastructure.model.entity.worldoftanks.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findById_TankIdAndId_Region(int tankId, String region);
}
