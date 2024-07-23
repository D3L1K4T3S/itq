package org.example.orders.models.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.orders.models.entity.OrderEntity;

import java.util.List;

@Getter
@Setter
public class ListOrdersResponse {
    private List<OrderResponse> orders;
}
