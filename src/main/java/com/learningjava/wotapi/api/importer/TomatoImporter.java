package com.learningjava.wotapi.api.importer;

import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.api.service.TomatoClient;
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

    private final TomatoClient tomatoApiClient;

    public TomatoImporter(TomatoClient tomatoApiClient) {
        this.tomatoApiClient = tomatoApiClient;
    }

    //Every night at 3:00
    @Scheduled(cron = "0 0 3 * * *")
    public void start() {
        logger.info("[Tomato Import] Started..");

        TomatoTankPerformanceResponse tankPerformanceEU = tomatoApiClient.getTankPerformance();

        if (Objects.isNull(tankPerformanceEU)) { return; }

        //TODO: To database

        logger.info("[Tomato Import] Finished successfully");
    }

    //On startup
    @EventListener(ApplicationReadyEvent.class)
    public void startupImport() {
        start();
    }
}
