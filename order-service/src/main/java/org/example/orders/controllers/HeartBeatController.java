package org.example.orders.controllers;

import org.example.orders.service.NumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiKeys.ORDERS)
public class HeartBeatController {

    private final NumberService numberService;

    public HeartBeatController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/")
    public ResponseEntity<String> test() {
        String response = numberService.getNumber();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
