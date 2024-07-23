package org.example.orders.models.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemEntity {
    private Long id;
    private Long article;
    private String name;
    private Long count;
    private Long price;
    private Long scale;
    private Long orderId;
}
