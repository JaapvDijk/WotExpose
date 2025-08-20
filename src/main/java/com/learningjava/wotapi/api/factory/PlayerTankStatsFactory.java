package com.learningjava.wotapi.api.factory;

import com.learningjava.wotapi.api.model.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerTankStatResponse;

import java.util.List;

public class PlayerTankStatsFactory {
    public static PlayerTankStatsResponse from(List<WoTPlayerTankStatResponse> rawStats) {
        var result = new PlayerTankStatsResponse();
        result.setData(rawStats);

        setTotalBattles(result);

        return result;
    }

    private static void setTotalBattles(PlayerTankStatsResponse result) {
        int totalAll = 0, totalClan = 0, totalStrongholdSkirmish = 0, totalRegularTeam = 0;
        int totalCompany = 0, totalStrongholdDefense = 0, totalTeam = 0, totalGlobalmap = 0;

        for (WoTPlayerTankStatResponse tank : result.getData()) {
            if (tank.all != null) totalAll += tank.all.battles;
            if (tank.clan != null) totalClan += tank.clan.battles;
            if (tank.strongholdSkirmish != null) totalStrongholdSkirmish += tank.strongholdSkirmish.battles;
            if (tank.regularTeam != null) totalRegularTeam += tank.regularTeam.battles;
            if (tank.company != null) totalCompany += tank.company.battles;
            if (tank.strongholdDefense != null) totalStrongholdDefense += tank.strongholdDefense.battles;
            if (tank.team != null) totalTeam += tank.team.battles;
            if (tank.globalmap != null) totalGlobalmap += tank.globalmap.battles;
        }

        result.setTotalBattlesAll(totalAll);
        result.setTotalBattlesClan(totalClan);
        result.setTotalBattlesStrongholdSkirmish(totalStrongholdSkirmish);
        result.setTotalBattlesRegularTeam(totalRegularTeam);
        result.setTotalBattlesCompany(totalCompany);
        result.setTotalBattlesStrongholdDefense(totalStrongholdDefense);
        result.setTotalBattlesTeam(totalTeam);
        result.setTotalBattlesGlobalmap(totalGlobalmap);
    }
}