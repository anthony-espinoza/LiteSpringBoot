package com.litethinking.orders.api.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, String> healthz() {
        return Map.of("status", "ok");
    }

}
