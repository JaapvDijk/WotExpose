package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.model.worldoftanks.dto.entity.Vehicle;
import com.learningjava.wotapi.api.repo.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle getVehicle(int tankId, String region) {
        return vehicleRepository.findById_TankIdAndId_Region(tankId, region)
                .orElseThrow(() -> new RuntimeException("Vehicle not found for:" + tankId + " " + region));
    }
}
