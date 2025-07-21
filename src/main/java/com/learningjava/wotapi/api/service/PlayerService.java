package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.mapper.PlayerResponseMapper;
import com.learningjava.wotapi.api.model.worldoftanks.dto.PlayerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final WargamingClient wgClient;

    public PlayerService(WargamingClient wargamingClient) {
        this.wgClient = wargamingClient;
    }

    public List<PlayerResponse> getPlayers(String name) {

        var players = wgClient.getPlayers(name);

        return PlayerResponseMapper.INSTANCE.toEntityList(players.getData());
    }

    public String getPlayerInfo(int id) {
        return wgClient.getPlayerInfo(id);
    }
}
