package org.example.orders.models.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.orders.models.entity.OrderEntity;

import java.util.Date;

@Getter
@Setter
public class GetOrdersByDateAnsSumRequest {
    private String currentDate;
    private Long sum;
    private Long scale;
}
