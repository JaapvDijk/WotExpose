package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.factory.PlayerTankStatsFactory;
import com.learningjava.wotapi.api.mapper.PlayerMapper;
import com.learningjava.wotapi.api.mapper.VehicleMapper;
import com.learningjava.wotapi.api.model.dto.PlayerSearchResponse;
import com.learningjava.wotapi.api.model.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerInfoResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTVehicleResponse;
import com.learningjava.wotapi.api.repo.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WargamingService {

    private final WargamingClient client;
    private final PlayerMapper playerMapper;
    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;

    public WargamingService(WargamingClient client,
                            PlayerMapper mapper,
                            VehicleMapper vehicleMapper,
                            VehicleRepository vehicleRepository) {
        this.client = client;
        this.playerMapper = mapper;
        this.vehicleMapper = vehicleMapper;
        this.vehicleRepository = vehicleRepository;
    }

    public List<WoTVehicleResponse> fetchVehicles() {
        return client.getVehicles();
    }

    public List<PlayerSearchResponse> getPlayers(String name) {
        var players = client.getPlayers(name);

        return playerMapper.toDtoList(players);
    }

    public WoTPlayerInfoResponse getPlayerInfo(int id) {
        return client.getPlayerInfo(id);
    }

    public PlayerTankStatsResponse getPlayerTankStats(int id) {
        return PlayerTankStatsFactory.from(client.getPlayerTanks(id));
    }

    public void saveVehicles(List<WoTVehicleResponse> vehicles, String region)
    {
        var localDate = LocalDate.now();
        var alreadyImportedToday = vehicleRepository.existsByImportDateAndRegionEquals(localDate, region);
        if (alreadyImportedToday) {
            return;
        }

        var result = vehicleMapper.toEntityList(vehicles);
        result = result.stream().peek(t ->
        {
            t.setImportDate(localDate);
            t.setRegion(region);
        }).toList();

        vehicleRepository.saveAll(result);
    }
}
