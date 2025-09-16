package com.learningjava.wotexpose.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {

    @GetMapping("")
    public ResponseEntity<Map<String, String>> get()
    {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
