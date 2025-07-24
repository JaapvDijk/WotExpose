package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.api.importer.TomatoImporter;
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

    @GetMapping("/import")
    public ResponseEntity<String> doImport(String region)
    {
        if (Objects.isNull(region)) { ResponseEntity.ok("Missing: 'region' query parameter"); }

        var success = tomatoImporter.start(region);

        if (!success) { ResponseEntity.internalServerError().body("Import failed"); }

        return ResponseEntity.ok("Import successful");
    }

    @Autowired
    public AdminController(TomatoImporter tomatoImporter) {
        this.tomatoImporter = tomatoImporter;
    }
}
