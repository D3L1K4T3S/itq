package org.example.orders.models.dto.request;

import java.util.Date;

public class OrderRequest {

    private Long id;
    private String number;
    private Long sum;
    private Long precision;
    private Date orderDate;
    private String recipient;
    private String address;
    private String payment;
    private String delivery;
}
