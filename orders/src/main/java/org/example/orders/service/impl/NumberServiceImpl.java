package org.example.orders.service.impl;

import org.example.orders.service.NumberService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumberServiceImpl implements NumberService {

    private final RestTemplate restTemplate;
    private final static String URL = "http://localhost:9090/number";

    public NumberServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getNumber(){
        return restTemplate.getForObject(URL, String.class);
    }
}
