package org.example.numbergenerateservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.slf4j.SLF4JLogger;
import org.example.numbergenerateservice.models.dto.response.NumberResponse;
import org.example.numbergenerateservice.service.GeneratorNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ApiKeys.NUMBERS)
public class GeneratorNumberController {

    private final GeneratorNumberService generatorNumberService;

    public GeneratorNumberController(GeneratorNumberService generatorNumberService) {
        this.generatorNumberService = generatorNumberService;
    }

    @GetMapping(ApiKeys.PATH_GENERATE)
    public ResponseEntity<String> getNumber() {
        log.info("Get request for create a new number");
        NumberResponse numberResponse = generatorNumberService.generate();
        return new ResponseEntity<>(numberResponse.getNumber(), HttpStatus.OK);
    }
}
