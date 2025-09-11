package com.learningjava.wotapi.infrastructure.persistance.repo;

import com.learningjava.wotapi.infrastructure.persistance.entity.worldoftanks.Vehicle;
import com.learningjava.wotapi.infrastructure.persistance.entity.worldoftanks.VehicleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findById(VehicleKey vehicleKey);
}
