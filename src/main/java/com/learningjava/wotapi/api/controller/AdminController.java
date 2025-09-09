package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.application.importer.TomatoImporter;
import com.learningjava.wotapi.application.importer.VehicleImporter;
import com.learningjava.wotapi.shared.constant.RegionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final TomatoImporter tomatoImporter;
    private final VehicleImporter vehiclesImporter;

    @Autowired
    public AdminController(TomatoImporter tomatoImporter,
                           VehicleImporter vehiclesImporter) {
        this.tomatoImporter = tomatoImporter;
        this.vehiclesImporter = vehiclesImporter;
    }

    @GetMapping("/import/tomato")
    public ResponseEntity<String> doImportTomato(RegionType region)
    {
        if (Objects.isNull(region)) { ResponseEntity.ok("Missing: 'region' query parameter"); }

        var success = tomatoImporter.start(region);

        if (!success) { ResponseEntity.internalServerError().body("Import failed"); }

        return ResponseEntity.ok("Import successful");
    }

    @GetMapping("/import/vehicle")
    public ResponseEntity<String> doImportVehicles(RegionType region)
    {
        if (Objects.isNull(region)) { ResponseEntity.ok("Missing: 'region' query parameter"); }

        var success = vehiclesImporter.start(region);

        if (!success) { ResponseEntity.internalServerError().body("Import failed"); }

        return ResponseEntity.ok("Import successful");
    }
}
