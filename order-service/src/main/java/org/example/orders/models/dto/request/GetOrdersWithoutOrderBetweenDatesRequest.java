package org.example.orders.models.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.orders.models.entity.OrderEntity;

import java.util.Date;

@Setter
@Getter
public class GetOrdersWithoutOrderBetweenDatesRequest {
    private String dateBefore;
    private String dateAfter;
    private OrderEntity order;
}
