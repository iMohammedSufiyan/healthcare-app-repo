package com.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GatewayController {

    @GetMapping("/gatewayEndpoint")
    public ResponseEntity<String> gatewayEndpoint() {
        return ResponseEntity.ok("Hello from the Gateway!");
    }

    // Other endpoints
}

