package com.learningjava.wotapi.application.service;

import com.learningjava.wotapi.shared.exception.VehicleNotFoundException;
import com.learningjava.wotapi.infrastructure.mapper.VehicleMapper;
import com.learningjava.wotapi.infrastructure.dto.worldoftanks.WoTVehicleResponse;
import com.learningjava.wotapi.infrastructure.model.entity.worldoftanks.Vehicle;
import com.learningjava.wotapi.infrastructure.repo.VehicleRepository;
import com.learningjava.wotapi.infrastructure.client.WargamingClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final WargamingClient client;
    
    public VehicleService(VehicleRepository vehicleRepository,
                          VehicleMapper vehicleMapper, WargamingClient client) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.client = client;
    }

    public Vehicle findVehicle(int tankId, String region) {
        return vehicleRepository.findById_TankIdAndId_Region(tankId, region)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found for:" + tankId + " " + region));
    }

    public List<WoTVehicleResponse> fetchVehicles() {
        return client.getVehicles();
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
