package org.example.orders.models.entity.base;

import java.util.Date;

public class BaseOrderEntity {
    private final Long id;
    private final String number;
    private final Long sum;
    private final Long scale;
    private final Date orderDate;
    private final String recipient;
    private final String address;
    private final String payment;
    private final String delivery;

    public BaseOrderEntity(
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
        this.id = id;
        this.number = number;
        this.sum = sum;
        this.scale = scale;
        this.orderDate = orderDate;
        this.recipient = recipient;
        this.address = address;
        this.payment = payment;
        this.delivery = delivery;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Long getSum() {
        return sum;
    }

    public Long getScale() {
        return scale;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getAddress() {
        return address;
    }

    public String getPayment() {
        return payment;
    }

    public String getDelivery() {
        return delivery;
    }
}
