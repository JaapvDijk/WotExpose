package com.learningjava.wotapi.application.importer;

import com.learningjava.wotapi.application.service.TomatoService;
import com.learningjava.wotapi.shared.constant.RegionType;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;
//test
@Component
public class TomatoImporter {

    private static final Logger logger = LoggerFactory.getLogger(TomatoImporter.class);

    private final TomatoService tomatoService;

    public TomatoImporter(TomatoService tomatoService) {
        this.tomatoService = tomatoService;
    }

    @Scheduled(cron = "${api.tomato.schedule-expression}")
    public boolean start() {
        return Arrays.stream(RegionType.values())
                .allMatch(this::start);
    }

    @Profile("!test")
    @EventListener(ApplicationReadyEvent.class)
    public boolean startupImport() {
        return start();
    }

    public boolean start(RegionType region) {
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
}
