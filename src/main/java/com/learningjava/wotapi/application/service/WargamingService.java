package com.learningjava.wotapi.application.service;

import com.learningjava.wotapi.application.dto.PlayerInfoResponse;
import com.learningjava.wotapi.application.calculator.PlayerTankStatsCalculator;
import com.learningjava.wotapi.application.mapper.PlayerMapper;
import com.learningjava.wotapi.application.dto.PlayerSearchResponse;
import com.learningjava.wotapi.application.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.infrastructure.client.wargaming.WargamingClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WargamingService {

    private final WargamingClient client;
    private final PlayerMapper playerMapper;
    private final ObjectProvider<PlayerTankStatsCalculator> statsCalculatorProvider;

    public WargamingService(WargamingClient client,
                            PlayerMapper mapper,
                            ObjectProvider<PlayerTankStatsCalculator> statsCalculatorProvider) {
        this.client = client;
        this.playerMapper = mapper;
        this.statsCalculatorProvider = statsCalculatorProvider;
    }

    public List<PlayerSearchResponse> getPlayers(String name) {
        var players = client.getPlayers(name);

        return playerMapper.toPlayerSearchResponseList(players);
    }

    public PlayerInfoResponse getPlayerInfo(int id) {
        var result = client.getPlayerInfo(id);

        return playerMapper.toPlayerInfoResponse(result);
    }

    public PlayerTankStatsResponse getPlayerTankStats(int id) {
        var result = client.getPlayerTanks(id);

        var statsCalculator = statsCalculatorProvider.getObject();

        return statsCalculator.calculate(result);
    }
}
