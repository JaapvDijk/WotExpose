package com.learningjava.wotapi.api.repo;

import com.learningjava.wotapi.api.model.tomato.entity.TankPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface TomatoTankPerformanceRepository extends JpaRepository<TankPerformance, Long> {
    boolean existsByImportDate(LocalDate importDate);

    TankPerformance findLatestByName(String name);

    boolean existsByImportDateAndRegionEquals(LocalDate importDate, String region);
}