package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.factory.PlayerTankStatsFactory;
import com.learningjava.wotapi.api.mapper.PlayerMapper;
import com.learningjava.wotapi.api.model.dto.PlayerSearchResponse;
import com.learningjava.wotapi.api.model.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WargamingService {

    private final WargamingClient client;
    private final PlayerMapper playerMapper;
    private final PlayerTankStatsFactory playerTankStatsFactory;

    public WargamingService(WargamingClient client,
                            PlayerMapper mapper,
                            PlayerTankStatsFactory playerTankStatsFactory) {
        this.client = client;
        this.playerMapper = mapper;
        this.playerTankStatsFactory = playerTankStatsFactory;
    }

    public List<PlayerSearchResponse> getPlayers(String name) {
        var players = client.getPlayers(name);

        return playerMapper.toDtoList(players);
    }

    public WoTPlayerInfoResponse getPlayerInfo(int id) {
        return client.getPlayerInfo(id);
    }

    public PlayerTankStatsResponse getPlayerTankStats(int id) {
        var result = client.getPlayerTanks(id);

        return playerTankStatsFactory.from(result);
    }
}
