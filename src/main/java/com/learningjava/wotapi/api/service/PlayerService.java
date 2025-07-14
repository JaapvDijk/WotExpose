package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.model.worldoftanks.entity.Players;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final WargamingClient wgClient;

    public PlayerService(WargamingClient wargamingClient) {
        this.wgClient = wargamingClient;
    }

    public Players GetPlayers(String name) {
        return wgClient.getPlayers(name);
    }

    public String GetPlayerInfo(int id) {
        return wgClient.getPlayerInfo(id);
    }
}
