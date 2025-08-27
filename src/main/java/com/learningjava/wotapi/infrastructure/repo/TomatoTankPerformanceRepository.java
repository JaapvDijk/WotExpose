package com.learningjava.wotapi.infrastructure.repo;

import com.learningjava.wotapi.infrastructure.model.entity.tomato.TankPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TomatoTankPerformanceRepository extends JpaRepository<TankPerformance, Long> {
    boolean existsByImportDateAndRegionEquals(LocalDate localDate, String region);

    TankPerformance getLatestByTankIdAndRegionEquals(int tankId, String region);
}