package org.example.orders.service;

import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.GetOrdersRequest;

public interface OrderValidatorService {
    Boolean isValidCreateOrderRequest(CreateOrderRequest createOrderRequest);
    Boolean isValidGetOrdersRequest(GetOrdersRequest orderRequest);
    Boolean isValidSum(Long sum);
    Boolean isValidScale(Long scale);
    Boolean isValidDate(String date);
}
