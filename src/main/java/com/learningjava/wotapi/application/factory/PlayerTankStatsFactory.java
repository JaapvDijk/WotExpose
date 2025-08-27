package com.learningjava.wotapi.application.factory;

import com.learningjava.wotapi.application.dto.PlayerTankStatResponse;
import com.learningjava.wotapi.application.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.infrastructure.dto.worldoftanks.WoTPlayerTankStatResponse;
import com.learningjava.wotapi.application.mapper.TankStatMapper;
import com.learningjava.wotapi.infrastructure.HttpContext;
import com.learningjava.wotapi.infrastructure.model.value.StatTotals;
import com.learningjava.wotapi.application.service.TomatoService;
import com.learningjava.wotapi.application.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

        var tankStats = tankStatMapper.toPlayerTankStatList(wotTankStats);
        tankStats.sort(comparingInt((PlayerTankStatResponse s) -> s.getAll().getBattles()).reversed());

        var result = new PlayerTankStatsResponse();
        result.setData(tankStats);

        var totals = new StatTotals();
        for (PlayerTankStatResponse tank : result.getData()) {
            var battleInfo = tank.getAll();
            var vehicleInfo = vehicleService.findVehicle(tank.getTankId(), region);
            var tomatoInfo = tomatoService.getLatestTankPerformance(tank.getTankId(), region);

            if (battleInfo != null) {
                totals.addBattles(
                        vehicleInfo.getType(),
                        vehicleInfo.getTier(),
                        battleInfo.getBattles()
                );
            }
        }

        totals.calculatePercentages();

        result.setTotals(totals);

        return result;
    }
}