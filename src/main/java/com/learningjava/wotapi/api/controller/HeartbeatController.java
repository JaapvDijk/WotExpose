package com.learningjava.wotapi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {

    public HeartbeatController() { }

    @GetMapping("")
    public ResponseEntity<String> get()
    {
        return ResponseEntity.ok("ok");
    }
}
