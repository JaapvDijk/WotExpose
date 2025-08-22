package com.learningjava.wotapi.api.repo;

import com.learningjava.wotapi.api.model.worldoftanks.dto.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    boolean existsByImportDateAndRegionEquals(LocalDate localDate, String region);
}
