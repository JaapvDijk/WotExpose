package com.learningjava.wotapi.application.service;

import com.learningjava.wotapi.infrastructure.model.entity.worldoftanks.VehicleKey;
import com.learningjava.wotapi.shared.constant.Region;
import com.learningjava.wotapi.shared.exception.VehicleNotFoundException;
import com.learningjava.wotapi.infrastructure.mapper.VehicleMapper;
import com.learningjava.wotapi.infrastructure.model.dto.worldoftanks.WoTVehicleResponse;
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

    public Vehicle findVehicle(int tankId, Region region) {
        var key = new VehicleKey(tankId, region);
        return vehicleRepository.findById(key)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found for:" + tankId + " " + region));
    }

    public List<WoTVehicleResponse> fetchVehicles() {
        return client.getVehicles();
    }

    public void saveVehicles(List<WoTVehicleResponse> vehicles, Region region)
    {
        vehicles.forEach(vehicle -> {
            var result = vehicleMapper.toEntity(vehicle);
            result.getId().setRegion(region);

            vehicleRepository.save(result);
        });
    }
}
