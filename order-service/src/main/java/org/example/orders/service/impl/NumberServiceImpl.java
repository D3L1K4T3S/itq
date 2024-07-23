package org.example.orders.service.impl;

import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.example.orders.exceptions.NoUniqueNumbersLeftException;
import org.example.orders.service.NumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
@Slf4j
public class NumberServiceImpl implements NumberService {

    private final RestClient restClient;

    private final static String URL = "http://localhost:9090/v1/numbers/number";

    public NumberServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public String getNumber() {
        try{
            return restClient.get().uri(URL).retrieve().toEntity(String.class).getBody();
        } catch (RuntimeException exception) {
            log.error("Can't get number");
            throw new NoUniqueNumbersLeftException("Error in number-generate service");
        }
    }
}
