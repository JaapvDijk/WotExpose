package com.learningjava.wotexpose.integration.importer;

import com.learningjava.wotexpose.application.importer.TomatoImporter;
import com.learningjava.wotexpose.infrastructure.persistance.entity.tomato.TankPerformance;
import com.learningjava.wotexpose.infrastructure.persistance.repo.TomatoTankPerformanceRepository;
import com.learningjava.wotexpose.integration.IntegrationTestBase;
import com.learningjava.wotexpose.shared.constant.RegionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Tag("failsOften") //because of the often invalid ID (in the base tomato URL, need to scrape it)
class TomatoImporterTests extends IntegrationTestBase {

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
            Assertions.assertNotNull(p.getImportDate(), "importDate should not be null");
            Assertions.assertTrue(p.getTankId() > 0, "tankId should be greater than 0");
            Assertions.assertFalse(p.getName().isBlank(), "name should not be blank");
            Assertions.assertTrue(p.getTier() >= 1 && p.getTier() <= 10, "tier should be between 1 and 10");
            Assertions.assertTrue(p.getWinrate() >= 0 && p.getWinrate() <= 100, "winrate should be between 0 and 100");
            Assertions.assertTrue(p.getBattles() >= 0, "battles should be non-negative");
            Assertions.assertTrue(p.getDirectHits() <= p.getShotsFired(), "directHits cannot exceed shotsFired");
            Assertions.assertTrue(p.getPenetrations() <= p.getDirectHits(), "penetrations cannot exceed directHits");
            Assertions.assertTrue(p.getWn8() >= 0, "wn8 should be non-negative");
            Assertions.assertTrue(p.getDamage() >= 0, "damage should be non-negative");
            Assertions.assertTrue(p.getSurvival() >= 0 && p.getSurvival() <= 100, "survival should be between 0 and 100");
        });
    }
}