package com.learningjava.wotapi.api.importer;

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

    @Scheduled(cron = "${api.tomato.schedule-expression}")
    public void start() {
        start("EU");
        start("NA");
        start("ASIA");
    }

    public boolean start(String region) {
        logger.info("[Tomato Import] Started for {}", region);

        var tankPerformance = tomatoService.fetchTankPerformance(region);

        if (Objects.isNull(tankPerformance)) {
            logger.info("[Tomato Import] aborted: Api call returned null {}", region);
            return false;
        }

        tomatoService.saveTankPerformance(tankPerformance, region);

        logger.info("[Tomato Import] Finished successfully for {}", region);

        return true;
    }

    //On startup
    @EventListener(ApplicationReadyEvent.class)
    public void startupImport() {
        start();
    }
}
