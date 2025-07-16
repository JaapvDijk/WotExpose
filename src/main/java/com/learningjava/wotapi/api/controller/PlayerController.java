package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.api.importer.TomatoImporter;
import com.learningjava.wotapi.api.model.dto.PlayerSearchRequest;
import com.learningjava.wotapi.api.model.worldoftanks.dto.PlayerResponse;
import com.learningjava.wotapi.api.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final TomatoImporter tomatoImporter;

    @Autowired
    public PlayerController(PlayerService playerService, TomatoImporter tomatoImporter) {
        this.playerService = playerService;
        this.tomatoImporter = tomatoImporter;
    }

    @GetMapping("/search")
    public ResponseEntity<List<PlayerResponse>> search(@Valid @ModelAttribute PlayerSearchRequest request)
    {
        var result = playerService.getPlayers(request.getName());

        if (result == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<String> getPlayer(@PathVariable int id, @RequestParam String region)
    {
        return new ResponseEntity<String>(playerService.getPlayerInfo(id), HttpStatus.OK);
    }

    @GetMapping("/tomatoImport")
    public ResponseEntity<String> doImport()
    {
        tomatoImporter.start();
        return ResponseEntity.ok("Done");
    }
}
