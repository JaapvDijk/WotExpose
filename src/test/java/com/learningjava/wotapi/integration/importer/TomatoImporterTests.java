package com.learningjava.wotapi.integration.importer;

import com.learningjava.wotapi.application.importer.TomatoImporter;
import com.learningjava.wotapi.infrastructure.persistance.entity.tomato.TankPerformance;
import com.learningjava.wotapi.infrastructure.persistance.repo.TomatoTankPerformanceRepository;
import com.learningjava.wotapi.shared.constant.RegionType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Tag("failsOften") //because of the often invalid ID (in the base tomato URL, need to scrape it)
@ActiveProfiles("test")
@Transactional
class TomatoImporterTests {

    @Autowired
    private TomatoImporter importer;

    @Autowired
    private TomatoTankPerformanceRepository repo;

    @Test
    void importerShouldSaveTankPerformanceForAllRegions() {
        boolean success = importer.start();
        Assertions.assertTrue(success);

        var tankPerformances = repo.findAll();
        Assertions.assertNotNull(tankPerformances);
        Assertions.assertFalse(tankPerformances.isEmpty());

        var regions = tankPerformances.stream().map(TankPerformance::getRegion).toList();

        for (RegionType region : RegionType.values()) {
            Assertions.assertTrue(regions.contains(region), "Missing region: " + region);
        }

        tankPerformances.forEach(p -> {
            Assertions.assertNotNull(p.getImportDate());
            Assertions.assertTrue(p.getTankId() > 0);
            Assertions.assertFalse(p.getName().isBlank());
            Assertions.assertTrue(p.getTier() >= 1 && p.getTier() <= 10);
            Assertions.assertTrue(p.getWinrate() >= 0 && p.getWinrate() <= 100);
            Assertions.assertTrue(p.getBattles() >= 0);
            Assertions.assertTrue(p.getDirectHits() <= p.getShotsFired());
            Assertions.assertTrue(p.getPenetrations() <= p.getDirectHits());
            Assertions.assertTrue(p.getWn8() >= 0);
            Assertions.assertTrue(p.getDamage() >= 0);
            Assertions.assertTrue(p.getSurvival() >= 0 && p.getSurvival() <= 100);
        });
    }
}