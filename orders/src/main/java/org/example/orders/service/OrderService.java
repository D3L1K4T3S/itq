package org.example.orders.service;

import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.OrderRequest;
import org.example.orders.models.dto.response.OrderResponse;

import java.util.Date;
import java.util.List;

public interface OrderService {
    void createOrder(CreateOrderRequest createOrderRequest);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getOrders(String orderDate, Long price, Long precision);
    List<OrderResponse> getOrdersWithoutOrder(Date orderDate, OrderRequest orderRequest);
}
