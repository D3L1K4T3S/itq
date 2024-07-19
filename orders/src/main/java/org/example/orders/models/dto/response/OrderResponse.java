package org.example.orders.models.dto.response;

import java.util.Date;

public class OrderResponse {
    private Long id;
    private String number;
    private Long sum;
    private Long scale;
    private Date orderDate;
    private String recipient;
    private String address;
    private String payment;
    private String delivery;

    public OrderResponse(Long id, String number, Long sum, Long scale, Date orderDate, String recipient, String address, String payment, String delivery) {
        this.id = id;
        this.number = number;
        this.orderDate = orderDate;
        this.recipient = recipient;
        this.address = address;
        this.payment = payment;
        this.delivery = delivery;
        this.sum = sum;
        this.scale = scale;
    }
}
