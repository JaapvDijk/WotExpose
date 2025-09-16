package com.learningjava.wotexpose.infrastructure.persistance;

import com.learningjava.wotexpose.shared.constant.RegionType;
import com.learningjava.wotexpose.infrastructure.persistance.entity.worldoftanks.Vehicle;
import com.learningjava.wotexpose.infrastructure.persistance.entity.worldoftanks.VehicleKey;
import com.learningjava.wotexpose.infrastructure.persistance.repo.VehicleRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder {
    private final VehicleRepository vehicleRepository;

    public DbSeeder(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        addMissingVehicles();
    }

    private void addMissingVehicles() {
        //TODO: add missing for NA & ASIA
        //TODO Add -64833 research what it is
        var missingVehicles = new ArrayList<>(List.of(
            new Vehicle(LocalDate.now(), new VehicleKey(17153, RegionType.EU), "Obj. 430B", "Object 430B", "ussr", false, "", "mediumTank", 10, true, new Vehicle.Images()),
            new Vehicle(LocalDate.now(), new VehicleKey(16913, RegionType.EU), "Waffenträger auf E 100", "Waffenträger auf E 100", "germany", false, "", "AT-SPG", 10, true, new Vehicle.Images()),
            new Vehicle(LocalDate.now(), new VehicleKey(14337, RegionType.EU), "Object 263B", "Object 263B", "ussr", false, "", "AT-SPG", 10, true, new Vehicle.Images()),
            new Vehicle(LocalDate.now(), new VehicleKey(12033, RegionType.EU), "SU-122-5", "SU-122-5", "ussr", false, "", "AT-SPG", 9, true, new Vehicle.Images()),
            new Vehicle(LocalDate.now(), new VehicleKey(56833, RegionType.EU), "T-44-122", "T-44-122", "ussr", true, "", "AT-SPG", 7, true, new Vehicle.Images())
        ));

        vehicleRepository.saveAll(missingVehicles);
    }
}