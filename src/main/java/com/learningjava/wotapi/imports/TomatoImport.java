package com.learningjava.wotapi.imports;

import com.learningjava.wotapi.api.model.Tomato.TomatoTankPerformance;
import com.learningjava.wotapi.service.TomatoClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Component
public class TomatoImport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TomatoClient tomatoApiClient;

    public TomatoImport(TomatoClient tomatoApiClient) {
        this.tomatoApiClient = tomatoApiClient;
    }

    //Every night at 3:00
    @Scheduled(cron = "0 0 3 * * *")
    public void start() {
        logger.info("[Tomato Import] Started..");

        Optional<TomatoTankPerformance> tankPerformanceEU = tomatoApiClient.getTankPerformance();

        if (tankPerformanceEU.isEmpty()) { return; }

        //TODO: To database

        logger.info("[Tomato Import] Finished successfully");
    }

    //On startup
    @EventListener(ApplicationReadyEvent.class)
    public void startupImport() {
        start();
    }
}
