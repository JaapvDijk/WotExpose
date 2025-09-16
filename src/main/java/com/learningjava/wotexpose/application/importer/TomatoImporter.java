package com.learningjava.wotexpose.application.importer;

import com.learningjava.wotexpose.application.service.TomatoService;
import com.learningjava.wotexpose.shared.constant.RegionType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

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
