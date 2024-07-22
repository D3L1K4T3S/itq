package org.example.orders.service.impl;

import org.example.orders.service.NumberService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NumberServiceImpl implements NumberService {

    private final RestClient restClient;

    private final static String URL = "http://localhost:9090/number";

    public NumberServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getNumber(){
        return restClient.get().uri(URL).retrieve().toEntity(String.class).getBody();
    }
}
