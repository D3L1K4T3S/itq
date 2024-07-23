package org.example.orders.service;

import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.GetOrdersByDateAnsSumRequest;
import org.example.orders.models.dto.request.GetOrdersWithoutOrderBetweenDatesRequest;
import org.example.orders.models.dto.response.ListOrdersResponse;
import org.example.orders.models.dto.response.OrderResponse;

public interface OrderService {
    void create(CreateOrderRequest createOrderRequest);
    OrderResponse getById(Long id);
    ListOrdersResponse getByDateAnsSum(GetOrdersByDateAnsSumRequest getOrdersRequest);
    ListOrdersResponse getWithoutOrderAndBetweenDates(GetOrdersWithoutOrderBetweenDatesRequest getOrdersRequest);
}
