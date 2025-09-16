package com.learningjava.wotexpose.application.calculator;

import com.learningjava.wotexpose.application.dto.PlayerTankStatResponse;
import com.learningjava.wotexpose.application.dto.PlayerTankStatsResponse;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTPlayerTankStatResponse;
import com.learningjava.wotexpose.application.mapper.TankStatMapper;
import com.learningjava.wotexpose.infrastructure.HttpContext;
import com.learningjava.wotexpose.infrastructure.client.tomato.dto.value.StatTotals;
import com.learningjava.wotexpose.application.service.TomatoService;
import com.learningjava.wotexpose.application.service.VehicleService;
import com.learningjava.wotexpose.shared.constant.RegionType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Comparator.comparingInt;

@Component
@Scope("prototype")
public class PlayerTankStatsCalculator {

    private final VehicleService vehicleService;
    private final TomatoService tomatoService;
    private final TankStatMapper tankStatMapper;

    public PlayerTankStatsCalculator(VehicleService vehicleService,
                                     TomatoService tomatoService,
                                     TankStatMapper tankStatMapper) {
        this.vehicleService = vehicleService;
        this.tomatoService = tomatoService;
        this.tankStatMapper = tankStatMapper;
    }

    public PlayerTankStatsResponse calculate(List<WoTPlayerTankStatResponse> wotTankStats) {
        RegionType region = HttpContext.getRegion();

        var tankStats = tankStatMapper.toPlayerTankStatList(wotTankStats);
        tankStats.sort(comparingInt((PlayerTankStatResponse s) -> s.getAll().getBattles()).reversed());

        var result = new PlayerTankStatsResponse();
        result.setData(tankStats);

        var totals = new StatTotals();
        for (PlayerTankStatResponse tank : result.getData()) {
            var battleInfo = tank.getAll();
            var vehicleInfo = vehicleService.findVehicle(tank.getTankId(), region);
            var tomatoInfo = tomatoService.findLatestTankPerformance(tank.getTankId(), region);

            if (vehicleInfo.isPresent() && tomatoInfo.isPresent()) {
                totals.addBattles(
                        vehicleInfo.get().getType(),
                        vehicleInfo.get().getTier(),
                        battleInfo.getBattles()
                );
            }
        }

        totals.calculatePercentages();

        result.setTotals(totals);

        return result;
    }
}