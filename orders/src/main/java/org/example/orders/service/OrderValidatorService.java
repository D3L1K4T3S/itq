package org.example.orders.service;

import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.OrderRequest;

import java.util.Date;

public interface OrderValidatorService {
    Boolean isValidCreateOrderRequest(CreateOrderRequest createOrderRequest);
    Boolean isValidOrderRequest(OrderRequest orderRequest);
    Boolean isValidSum(Long sum);
    Boolean isValidScale(Long scale);
    Boolean isValidDate(String date);
}
