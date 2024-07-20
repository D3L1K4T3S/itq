package org.example.orders.models.entity;

import org.example.orders.models.entity.base.BaseOrderEntity;

import java.util.Date;

public class OrderEntity extends BaseOrderEntity {
    public OrderEntity(
            Long id,
            String number,
            Long sum,
            Long scale,
            Date orderDate,
            String recipient,
            String address,
            String payment,
            String delivery
    ) {
        super(id, number, sum, scale, orderDate, recipient, address, payment, delivery);
    }
}
