package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.api.importer.TomatoImporter;
import com.learningjava.wotapi.api.model.dto.RequestBase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final TomatoImporter tomatoImporter;

    @GetMapping("/import")
    public ResponseEntity<String> doImport(@Valid @ModelAttribute RequestBase request)
    {
        var success = tomatoImporter.start(request.getRegion());

        if (!success) { ResponseEntity.ok("Import failed"); }

        return ResponseEntity.ok("Import successful");
    }

    @Autowired
    public AdminController(TomatoImporter tomatoImporter) {
        this.tomatoImporter = tomatoImporter;
    }
}
