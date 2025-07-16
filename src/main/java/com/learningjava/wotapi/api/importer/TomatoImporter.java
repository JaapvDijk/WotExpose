package com.learningjava.wotapi.api.importer;

import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.api.repo.TomatoTankPerformanceRepository;
import com.learningjava.wotapi.api.service.TomatoClient;
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
    @Scheduled(cron = "0 0 3 * * *")
    public void start() {
        logger.info("[Tomato Import] Started..");

        TomatoTankPerformanceResponse tankPerformanceEU = tomatoService.fetchTankPerformance();

        if (Objects.isNull(tankPerformanceEU)) {
            logger.info("[Tomato Import] aborted: Api call returned null..");
            return;
        }

        tomatoService.saveTankPerformance(tankPerformanceEU);

        //var tanks = tomatoService.getRecentTankPerformanceByName("Bourrasque");

        logger.info("[Tomato Import] Finished successfully");
    }

    //On startup
    @EventListener(ApplicationReadyEvent.class)
    public void startupImport() {
        start();
    }
}
