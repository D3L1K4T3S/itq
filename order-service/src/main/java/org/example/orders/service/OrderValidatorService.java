package org.example.orders.service;

import org.example.orders.models.dto.request.CreateOrderRequest;

public interface OrderValidatorService {
    Boolean isValidCreateOrderRequest(CreateOrderRequest createOrderRequest);
    Boolean isValidOrderRequest(GetWithoutOrderRequest orderRequest);
    Boolean isValidSum(Long sum);
    Boolean isValidScale(Long scale);
    Boolean isValidDate(String date);
}
