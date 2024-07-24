package org.example.orders.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.orders.models.enums.TypeDelivery;
import org.example.orders.models.enums.TypePayment;

import java.util.Date;

@Getter
@Setter
public class OrderEntity {
    private Integer id;
    private String number;
    private Long sum;
    private Long scale;
    private Date orderDate;
    private String recipient;
    private String address;
    private TypePayment payment;
    private TypeDelivery delivery;
}
