package org.example.orders.service;

import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.GetOrdersByDateAnsSumRequest;
import org.example.orders.models.dto.request.GetOrdersWithoutOrderBetweenDatesRequest;

public interface OrderValidatorService {
    Boolean isValidCreateOrderRequest(CreateOrderRequest createOrderRequest);
    Boolean isValidGetOrdersByDateAnsSumRequest(GetOrdersByDateAnsSumRequest orderRequest);
    Boolean isValidGetOrdersWithoutOrderBetweenDatesRequest(GetOrdersWithoutOrderBetweenDatesRequest orderRequest);

}
