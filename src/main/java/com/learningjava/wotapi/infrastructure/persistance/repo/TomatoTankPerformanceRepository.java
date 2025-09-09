package com.learningjava.wotapi.infrastructure.persistance.repo;

import com.learningjava.wotapi.infrastructure.persistance.entity.tomato.TankPerformance;
import com.learningjava.wotapi.shared.constant.RegionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TomatoTankPerformanceRepository extends JpaRepository<TankPerformance, Long> {
    boolean existsByImportDateAndRegionEquals(LocalDate localDate, RegionType region);

    Optional<TankPerformance> findLatestByTankIdAndRegionEquals(int tankId, RegionType region);
}