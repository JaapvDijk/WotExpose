package com.learningjava.wotapi.infrastructure.repo;

import com.learningjava.wotapi.infrastructure.model.entity.tomato.TankPerformance;
import com.learningjava.wotapi.shared.constant.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TomatoTankPerformanceRepository extends JpaRepository<TankPerformance, Long> {
    boolean existsByImportDateAndRegionEquals(LocalDate localDate, String region);

    TankPerformance getLatestByTankIdAndRegionEquals(int tankId, Region region);
}