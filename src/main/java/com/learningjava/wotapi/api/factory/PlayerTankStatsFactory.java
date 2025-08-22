package com.learningjava.wotapi.api.factory;

import com.learningjava.wotapi.api.HttpContext;
import com.learningjava.wotapi.api.mapper.TankStatMapper;
import com.learningjava.wotapi.api.model.dto.PlayerTankStatResponse;
import com.learningjava.wotapi.api.model.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerTankStatResponse;
import com.learningjava.wotapi.api.service.VehicleService;
import com.learningjava.wotapi.api.service.WargamingService;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Comparator.comparingInt;

@Component
public class PlayerTankStatsFactory {

    private final VehicleService vehicleService;
    private final TankStatMapper tankStatMapper;

    public PlayerTankStatsFactory(VehicleService vehicleService,
                                  TankStatMapper tankStatMapper) {
        this.vehicleService = vehicleService;
        this.tankStatMapper = tankStatMapper;
    }

    public PlayerTankStatsResponse from(List<WoTPlayerTankStatResponse> wotTankStats) {
        String region = HttpContext.getRegion();

        var tankStats = tankStatMapper.toPlayerTankStats(wotTankStats);
        tankStats.sort(comparingInt((PlayerTankStatResponse s) -> s.getAll().getBattles()).reversed());

        var result = new PlayerTankStatsResponse();
        result.setData(tankStats);

        int totalBattlesAll = 0;

        int totalBattlesLight = 0;
        int totalBattlesMedium = 0;
        int totalBattlesHeavy = 0;
        int totalBattlesSPG = 0;

        for (PlayerTankStatResponse tank : result.getData()) {
            var tankAll = tank.getAll(); //Because it contains the 15v15 random battles
            var vehicleInfo = vehicleService.getVehicle(tank.getTankId(), region);

            if (tankAll != null) {
                totalBattlesAll += tankAll.getBattles();
            }

//                totalBattlesLight += 0;
//                if (true) totalBattlesMedium += tank.;
//                totalBattlesHeavy += 0;
//                totalBattlesSPG += 0;
        }

        result.setTotalBattlesAll(totalBattlesAll);
        result.setTotalBattlesLight(totalBattlesLight);
        result.setTotalBattlesMedium(totalBattlesMedium);
        result.setTotalBattlesHeavy(totalBattlesHeavy);
        result.setTotalBattlesSPG(totalBattlesSPG);

        return result;
    }
}