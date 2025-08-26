package com.learningjava.wotapi.api.factory;

import com.learningjava.wotapi.api.HttpContext;
import com.learningjava.wotapi.api.mapper.TankStatMapper;
import com.learningjava.wotapi.api.model.dto.PlayerTankStatResponse;
import com.learningjava.wotapi.api.model.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.api.model.shared.StatTotals;
import com.learningjava.wotapi.api.model.worldoftanks.WoTPlayerTankStatResponse;
import com.learningjava.wotapi.api.service.TomatoClient;
import com.learningjava.wotapi.api.service.TomatoService;
import com.learningjava.wotapi.api.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparingInt;

@Component
@Scope("prototype")
public class PlayerTankStatsFactory {

    private final VehicleService vehicleService;
    private final TomatoService tomatoService;
    private final TankStatMapper tankStatMapper;
    private final Logger logger = LoggerFactory.getLogger(PlayerTankStatsFactory.class);

    public PlayerTankStatsFactory(VehicleService vehicleService,
                                  TomatoService tomatoService,
                                  TankStatMapper tankStatMapper) {
        this.vehicleService = vehicleService;
        this.tomatoService = tomatoService;
        this.tankStatMapper = tankStatMapper;
    }

    public PlayerTankStatsResponse from(List<WoTPlayerTankStatResponse> wotTankStats) {
        String region = HttpContext.getRegion();

        var tankStats = tankStatMapper.toPlayerTankStats(wotTankStats);
        tankStats.sort(comparingInt((PlayerTankStatResponse s) -> s.getAll().getBattles()).reversed());

        var result = new PlayerTankStatsResponse();
        result.setData(tankStats);

        var totals = new StatTotals();
        for (PlayerTankStatResponse tank : result.getData()) {
            var battleInfo = tank.getAll();
            var vehicleInfo = vehicleService.findVehicle(tank.getTankId(), region);
            var tomatoInfo = tomatoService.getLatestTankPerformance(tank.getTankId(), region);

            if (vehicleInfo.isEmpty()) {
                logger.error("[PlayerTankStatsFactory] tank_id not found in encyclopedia: " + tank.getTankId());
            }

            else if (battleInfo != null) {
                totals.addBattles(
                        vehicleInfo.get().getType(),
                        vehicleInfo.get().getTier(),
                        battleInfo.getBattles()
                );
            }

            if (tomatoInfo != null) {

            }
        }

        totals.calculatePercentages();

        result.setTotals(totals);

        return result;
    }
}