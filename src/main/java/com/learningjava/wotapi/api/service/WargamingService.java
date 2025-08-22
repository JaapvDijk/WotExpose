package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.factory.PlayerTankStatsFactory;
import com.learningjava.wotapi.api.mapper.PlayerMapper;
import com.learningjava.wotapi.api.mapper.TankStatMapper;
import com.learningjava.wotapi.api.mapper.VehicleMapper;
import com.learningjava.wotapi.api.model.dto.PlayerSearchResponse;
import com.learningjava.wotapi.api.model.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerInfoResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTVehicleResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.entity.Vehicle;
import com.learningjava.wotapi.api.repo.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WargamingService {

    private final WargamingClient client;
    private final PlayerMapper playerMapper;
    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;
    private final PlayerTankStatsFactory playerTankStatsFactory;

    public WargamingService(WargamingClient client,
                            PlayerMapper mapper,
                            VehicleMapper vehicleMapper,
                            TankStatMapper tankStatMapper,
                            VehicleRepository vehicleRepository,
                            PlayerTankStatsFactory playerTankStatsFactory) {
        this.client = client;
        this.playerMapper = mapper;
        this.vehicleMapper = vehicleMapper;
        this.vehicleRepository = vehicleRepository;
        this.playerTankStatsFactory = playerTankStatsFactory;
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
        var result = client.getPlayerTanks(id);

        return playerTankStatsFactory.from(result);
    }

    public Vehicle getVehicle(int tankId, String region) {
        return vehicleRepository.findById_TankIdAndId_Region(tankId, region)
                .orElseThrow(() -> new RuntimeException("Vehicle not found for:" + tankId + " " + region));
    }

    public void saveVehicles(List<WoTVehicleResponse> vehicles, String region)
    {
        vehicles.forEach(vehicle -> {
            var result = vehicleMapper.toEntity(vehicle);
            result.getId().setRegion(region);

            vehicleRepository.save(result);
        });
    }
}
