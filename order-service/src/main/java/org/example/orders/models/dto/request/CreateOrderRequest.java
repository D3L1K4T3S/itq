package org.example.orders.models.dto.request;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Date;

public class CreateOrderRequest {
    private Long sum;
    private Long scale;
    private String recipient;
    private String address;
    private String payment;
    private String delivery;
    private Long itemId;

    public Long getSum(){
        return sum;
    }
    public Long getScale(){
        return scale;
    }
    public String getRecipient(){
        return recipient;
    }
    public String getAddress(){
        return address;
    }
    public String getPayment(){
        return payment;
    }
    public String getDelivery(){
        return delivery;
    }

    public Long getItemId(){
        return itemId;
    }
}
