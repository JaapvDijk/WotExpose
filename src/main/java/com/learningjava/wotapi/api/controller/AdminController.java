package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.api.importer.TomatoImporter;
import com.learningjava.wotapi.api.model.dto.PlayerRequestBase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
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
