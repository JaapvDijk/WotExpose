package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.factory.PlayerTankStatsFactory;
import com.learningjava.wotapi.api.mapper.PlayerMapper;
import com.learningjava.wotapi.api.model.dto.PlayerSearchResponse;
import com.learningjava.wotapi.api.model.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerInfoResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerTankStatResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerTankStatsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WargamingService {

    private final WargamingClient client;
    private final PlayerMapper mapper;

    public WargamingService(WargamingClient client, PlayerMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public List<PlayerSearchResponse> getPlayers(String name) {
        var players = client.getPlayers(name);

        return mapper.toDtoList(players);
    }

    public WoTPlayerInfoResponse getPlayerInfo(int id) {
        return client.getPlayerInfo(id);
    }

    public PlayerTankStatsResponse getPlayerTankStats(int id) {
        return PlayerTankStatsFactory.from(client.getPlayerTanks(id));
    }
}
