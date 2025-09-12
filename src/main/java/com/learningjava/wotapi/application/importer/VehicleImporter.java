package com.learningjava.wotapi.application.importer;

import com.learningjava.wotapi.infrastructure.HttpContext;
import com.learningjava.wotapi.application.service.VehicleService;
import com.learningjava.wotapi.shared.constant.RegionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class VehicleImporter { //TODO: tests

    private static final Logger logger = LoggerFactory.getLogger(VehicleImporter.class);

    private final VehicleService vehicleService;

    public VehicleImporter(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Scheduled(cron = "${api.tomato.schedule-expression}")
    public boolean start() {
        return Arrays.stream(RegionType.values())
                .allMatch(this::start);
    }

    @Profile("!test")
    public boolean startupImport() {
        return start();
    }

    public boolean start(RegionType region) {
        HttpContext.setRegion(region);

        logger.info("[Vehicle Import] Started for {}", region);

        var vehicles = vehicleService.fetchVehicles(); //region?

        if (Objects.isNull(vehicles)) {
            logger.info("[Vehicle Import] aborted: Api call returned null {}", region);
            return false;
        }

        vehicleService.saveVehicles(vehicles, region);

        logger.info("[Vehicle Import] Finished successfully for {}", region);

        return true;
    }
}
