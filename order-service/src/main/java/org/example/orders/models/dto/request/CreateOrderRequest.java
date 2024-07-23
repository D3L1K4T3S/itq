package org.example.orders.models.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.orders.models.enums.TypeDelivery;
import org.example.orders.models.enums.TypePayment;

@Getter
@Setter
public class CreateOrderRequest {
    private Long sum;
    private Long scale;
    private String recipient;
    private String address;
    private TypePayment payment;
    private TypeDelivery delivery;
}
