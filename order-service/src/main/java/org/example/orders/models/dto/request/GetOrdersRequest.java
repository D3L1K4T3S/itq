package org.example.orders.models.dto.request;

import org.example.orders.models.entity.OrderEntity;

import java.util.Date;

public class GetOrdersRequest {
    Date date;
    Long sum;
    Long total;

    OrderEntity order;
}
