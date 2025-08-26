package com.learningjava.wotapi.api.importer;

import com.learningjava.wotapi.api.HttpContext;
import com.learningjava.wotapi.api.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
        return start("EU") &&
               start("NA") &&
               start("ASIA");
    }

    @Profile("!test")
    @EventListener(ApplicationReadyEvent.class)
    public boolean startupImport() {
        return start();
    }

    public boolean start(String region) {
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
