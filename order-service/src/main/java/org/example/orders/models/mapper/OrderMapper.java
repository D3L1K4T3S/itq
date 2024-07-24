package org.example.orders.models.mapper;

import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.response.ListOrdersResponse;
import org.example.orders.models.dto.response.OrderResponse;
import org.example.orders.models.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    public  OrderEntity toEntity(CreateOrderRequest order) {
        OrderEntity entity = new OrderEntity();
        entity.setSum(order.getSum());
        entity.setScale(order.getScale());
        entity.setRecipient(order.getRecipient());
        entity.setAddress(order.getAddress());
        entity.setPayment(order.getPayment());
        entity.setDelivery(order.getDelivery());
        return entity;
    }

    public  OrderResponse toResponse(OrderEntity order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setNumber(order.getNumber());
        response.setOrderDate(order.getOrderDate());
        response.setSum(order.getSum());
        response.setScale(order.getScale());
        response.setRecipient(order.getRecipient());
        response.setAddress(order.getAddress());
        response.setPayment(order.getPayment());
        response.setDelivery(order.getDelivery());
        return response;
    }

    public ListOrdersResponse toListResponse(List<OrderEntity> orders) {
        ListOrdersResponse response = new ListOrdersResponse();
        response.setOrders(new ArrayList<>());
        for(OrderEntity entity : orders) {
            response.getOrders().add(toResponse(entity));
        }
        return response;
    }

}
