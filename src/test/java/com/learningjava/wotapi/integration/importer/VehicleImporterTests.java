package com.learningjava.wotapi.integration.importer;

import com.learningjava.wotapi.application.importer.VehicleImporter;
import com.learningjava.wotapi.infrastructure.persistance.repo.VehicleRepository;
import com.learningjava.wotapi.integration.IntegrationTestBase;
import com.learningjava.wotapi.shared.constant.RegionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Tag("callerIpMustBeStatic")
class VehicleImporterTests extends IntegrationTestBase {

    @Autowired
    private VehicleImporter importer;

    @Autowired
    private VehicleRepository repo;

    @Test
    void importerShouldSaveVehiclesForAllRegions() {
        boolean success = importer.start();
        Assertions.assertTrue(success);

        var vehicles = repo.findAll();
        Assertions.assertNotNull(vehicles);
        Assertions.assertFalse(vehicles.isEmpty());

        var regions = vehicles.stream().map(v -> v.getId().getRegion()).toList();

        for (RegionType region : RegionType.values()) {
            Assertions.assertTrue(regions.contains(region), "Missing region: " + region);
        }

        vehicles.forEach(v -> {
            Assertions.assertNotNull(v.getImportDate(), "importDate should not be null");
            Assertions.assertTrue(v.getId().getTankId() > 0,"tankId should be greater than 0");
            Assertions.assertFalse(v.getName().isBlank(), "name should not be blank");
            //Assertions.assertTrue(v.getTier() >= 1 && v.getTier() <= 10,"tier should be between 1 and 10"); //TODO: is probably string, check tomatoImport test as well
            Assertions.assertNotNull(v.getShortName(), "shortName should not be null");
            Assertions.assertFalse(v.getShortName().isBlank(), "shortName should not be blank");
            Assertions.assertNotNull(v.getNation(), "nation should not be null");
            Assertions.assertFalse(v.getNation().isBlank(), "nation should not be blank");
            Assertions.assertNotNull(v.getType(), "type should not be null");
            Assertions.assertFalse(v.getType().isBlank(), "type should not be blank");
            Assertions.assertNotNull(v.getImages(), "images should not be null");
            Assertions.assertNotNull(v.getImages().getSmallIcon(), "smallIcon should not be null");
            Assertions.assertFalse(v.getImages().getSmallIcon().isBlank(), "smallIcon should not be blank");
            Assertions.assertNotNull(v.getImages().getContourIcon(), "contourIcon should not be null");
            Assertions.assertFalse(v.getImages().getContourIcon().isBlank(), "contourIcon should not be blank");
            Assertions.assertNotNull(v.getImages().getBigIcon(), "bigIcon should not be null");
            Assertions.assertFalse(v.getImages().getBigIcon().isBlank(), "bigIcon should not be blank");
        });
    }
}