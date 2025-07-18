package com.learningjava.wotapi.api.importer;

import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.api.service.TomatoService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Component
public class TomatoImporter {

    private static final Logger logger = LoggerFactory.getLogger(TomatoImporter.class);

    private final TomatoService tomatoService;

    public TomatoImporter(TomatoService tomatoService) {
        this.tomatoService = tomatoService;
    }

    //Every night at 3:00
    @Scheduled(cron = "0 0 3 * * *") //TODO: naar config
    public void start() {
        start("EU");
        start("NA");
        start("ASIA");
    }

    public boolean start(String region) {
        logger.info("[Tomato Import] Started for {} ..", region);

        TomatoTankPerformanceResponse tankPerformance = tomatoService.fetchTankPerformance(region);

        if (Objects.isNull(tankPerformance)) {
            logger.info("[Tomato Import] aborted: Api call returned null..");
            return false;
        }

        tomatoService.saveTankPerformance(tankPerformance, region);

        logger.info("[Tomato Import] Finished successfully");

        return true;
    }

    //On startup
    @EventListener(ApplicationReadyEvent.class)
    public void startupImport() {
        start();
    }
}
