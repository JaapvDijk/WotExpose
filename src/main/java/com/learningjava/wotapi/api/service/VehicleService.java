package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.mapper.VehicleMapper;
import com.learningjava.wotapi.api.model.worldoftanks.WoTVehicleResponse;
import com.learningjava.wotapi.api.model.worldoftanks.entity.Vehicle;
import com.learningjava.wotapi.api.repo.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Vehicle> findVehicle(int tankId, String region) {
        return vehicleRepository.findById_TankIdAndId_Region(tankId, region);
//                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found for:" + tankId + " " + region));
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
