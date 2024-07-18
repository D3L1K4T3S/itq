package org.example.numbergenerateservice.controllers;

import org.example.numbergenerateservice.models.dto.response.NumberResponse;
import org.example.numbergenerateservice.service.GeneratorNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorNumberController {

    private final GeneratorNumberService generatorNumberService;

    public GeneratorNumberController(GeneratorNumberService generatorNumberService) {
        this.generatorNumberService = generatorNumberService;
    }

    @GetMapping("/number")
    public ResponseEntity<String> getNumber() {
        NumberResponse numberResponse = generatorNumberService.generate();
        return new ResponseEntity<>(numberResponse.getNumber(), HttpStatus.OK);
    }
}
