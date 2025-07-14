package com.learningjava.wotapi.api.controller;

import com.learningjava.wotapi.api.model.worldoftanks.entity.Players;
import com.learningjava.wotapi.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/search")
    public ResponseEntity<Players> Search(@RequestParam String name, @RequestParam String region)
    {
        return new ResponseEntity<Players>(playerService.GetPlayers(name), HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<String> GetPlayer(@PathVariable int id, @RequestParam String region)
    {
        return new ResponseEntity<String>(playerService.GetPlayerInfo(id), HttpStatus.OK);
    }
}
