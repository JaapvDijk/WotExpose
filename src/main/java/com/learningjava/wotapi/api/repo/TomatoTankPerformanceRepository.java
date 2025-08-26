package com.learningjava.wotapi.api.repo;

import com.learningjava.wotapi.api.model.tomato.entity.TankPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TomatoTankPerformanceRepository extends JpaRepository<TankPerformance, Long> {
    boolean existsByImportDateAndRegionEquals(LocalDate localDate, String region);

    TankPerformance getLatestByTankIdAndRegionEquals(int tankId, String region);
}