package com.learningjava.wotapi.application.service;

import com.learningjava.wotapi.application.dto.PlayerInfoResponse;
import com.learningjava.wotapi.application.factory.PlayerTankStatsFactory;
import com.learningjava.wotapi.application.mapper.PlayerMapper;
import com.learningjava.wotapi.application.dto.PlayerSearchResponse;
import com.learningjava.wotapi.application.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.infrastructure.client.WargamingClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WargamingService {

    private final WargamingClient client;
    private final PlayerMapper playerMapper;
    private final ObjectProvider<PlayerTankStatsFactory> factoryProvider;

    public WargamingService(WargamingClient client,
                            PlayerMapper mapper,
                            ObjectProvider<PlayerTankStatsFactory> playerTankStatsFactory) {
        this.client = client;
        this.playerMapper = mapper;
        this.factoryProvider = playerTankStatsFactory;
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

        var playerTankStatsFactory = factoryProvider.getObject();

        return playerTankStatsFactory.from(result);
    }
}
