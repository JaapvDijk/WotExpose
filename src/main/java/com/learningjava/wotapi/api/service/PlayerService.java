package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.mapper.PlayerMapper;
import com.learningjava.wotapi.api.model.worldoftanks.dto.PlayerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final WargamingClient client;
    private final PlayerMapper mapper;

    public PlayerService(WargamingClient client, PlayerMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public List<PlayerResponse> getPlayers(String name) {

        var players = client.getPlayers(name);

        return mapper.toDtoList(players.getData());
    }

    public String getPlayerInfo(int id) {
        return client.getPlayerInfo(id);
    }
}
