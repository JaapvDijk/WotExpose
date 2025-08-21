package com.learningjava.wotapi.api.factory;

import com.learningjava.wotapi.api.model.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerTankStatResponse;

import java.util.List;

import static java.util.Comparator.comparingInt;

public class PlayerTankStatsFactory {
    public static PlayerTankStatsResponse from(List<WoTPlayerTankStatResponse> rawStats) {
        rawStats.sort(comparingInt((WoTPlayerTankStatResponse s) -> s.all.battles).reversed());

        var result = new PlayerTankStatsResponse();
        result.setData(rawStats);

        int totalBattlesAll = 0;

        for (WoTPlayerTankStatResponse tankAny : result.getData()) {
            var tank = tankAny.all; //Because it contains the 15v15 random battles

            if (tank != null) {
                totalBattlesAll += tank.battles;
            }
        }

        result.setTotalBattlesAll(totalBattlesAll);

        return result;
    }
}