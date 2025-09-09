package com.learningjava.wotapi.application.service;

import com.learningjava.wotapi.infrastructure.persistance.entity.worldoftanks.VehicleKey;
import com.learningjava.wotapi.shared.constant.RegionType;
import com.learningjava.wotapi.infrastructure.persistance.mapper.VehicleMapper;
import com.learningjava.wotapi.infrastructure.client.wargaming.dto.WoTVehicleResponse;
import com.learningjava.wotapi.infrastructure.persistance.entity.worldoftanks.Vehicle;
import com.learningjava.wotapi.infrastructure.persistance.repo.VehicleRepository;
import com.learningjava.wotapi.infrastructure.client.wargaming.WargamingClient;
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

    public Optional<Vehicle> findVehicle(int tankId, RegionType region) {
        var key = new VehicleKey(tankId, region);
        return vehicleRepository.findById(key);
    }

    public List<WoTVehicleResponse> fetchVehicles() {
        return client.getVehicles();
    }

    public void saveVehicles(List<WoTVehicleResponse> vehicles, RegionType region)
    {
        vehicles.forEach(vehicle -> {
            var result = vehicleMapper.toEntity(vehicle);
            result.getId().setRegion(region);

            vehicleRepository.save(result);
        });
    }
}
