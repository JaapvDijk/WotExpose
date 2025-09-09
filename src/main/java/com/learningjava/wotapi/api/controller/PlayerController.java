package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.application.dto.*;

import com.learningjava.wotapi.application.dto.PlayerSearchRequest;
import com.learningjava.wotapi.application.dto.PlayerTankStatsResponse;
import com.learningjava.wotapi.application.service.WargamingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final WargamingService wargamingService;

    @Autowired
    public PlayerController(WargamingService wargamingService) {
        this.wargamingService = wargamingService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<PlayerSearchResponse>> search(@Valid @ModelAttribute PlayerSearchRequest request)
    {
        var result = wargamingService.getPlayers(request.getName());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<PlayerInfoResponse> getInfo(@PathVariable Integer id, @Valid @ModelAttribute PlayerRequest request)
    {
        var result = wargamingService.getPlayerInfo(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/tanks/{id}")
    public ResponseEntity<PlayerTankStatsResponse> getTankStats(@PathVariable Integer id, @Valid @ModelAttribute PlayerRequest request)
    {
        var result = wargamingService.getPlayerTankStats(id);

        return ResponseEntity.ok(result);
    }
}
