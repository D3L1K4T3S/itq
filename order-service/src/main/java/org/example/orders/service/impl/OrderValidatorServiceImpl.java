package org.example.orders.service.impl;

import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.GetOrdersByDateAnsSumRequest;
import org.example.orders.models.dto.request.GetOrdersWithoutOrderBetweenDatesRequest;
import org.example.orders.models.entity.OrderEntity;
import org.example.orders.models.enums.TypeDelivery;
import org.example.orders.models.enums.TypePayment;
import org.example.orders.service.OrderValidatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class OrderValidatorServiceImpl implements OrderValidatorService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Boolean isValidCreateOrderRequest(CreateOrderRequest createOrderRequest) {
        return isValidAdditionalInfo(createOrderRequest)
                && createOrderRequest.getSum() > 0
                && createOrderRequest.getScale() > 0;
    }

    @Override
    public Boolean isValidGetOrdersByDateAnsSumRequest(GetOrdersByDateAnsSumRequest orderRequest) {
        return isValidDate(orderRequest.getCurrentDate())
                && orderRequest.getSum() > 0
                && orderRequest.getScale() > 0;
    }

    @Override
    public Boolean isValidGetOrdersWithoutOrderBetweenDatesRequest(GetOrdersWithoutOrderBetweenDatesRequest orderRequest) {
        return isValidDate(orderRequest.getDateBefore())
                && isValidDate(orderRequest.getDateAfter())
                && isValidOrder(orderRequest.getOrder());
    }

    private Boolean isValidOrder(OrderEntity order) {
        return isValidNumber(order.getNumber());
    }

    private Boolean isValidAdditionalInfo(CreateOrderRequest createOrderRequest){
        return isValidAddress(createOrderRequest.getAddress())
                && isValidRecipient(createOrderRequest.getRecipient())
                && isValidPayment(createOrderRequest.getPayment())
                && isValidDelivery(createOrderRequest.getDelivery());
    }

    private Boolean isValidNumber(String number) {
        return !number.isBlank() && (number.length() == 13 || number.length() == 12);
    }

    private Boolean isValidAddress(String address) {
        return !address.isBlank() && !address.isEmpty() && address.length() < 256;
    }

    private Boolean isValidRecipient(String recipient) {
        return !recipient.isBlank() && !recipient.isEmpty() && recipient.length() < 256;
    }

    private Boolean isValidPayment(TypePayment payment) {
        return payment == TypePayment.ONLINE || payment == TypePayment.UPON_RECEIPT;
    }

    private Boolean isValidDelivery(TypeDelivery delivery) {
        return delivery == TypeDelivery.EXPRESS || delivery == TypeDelivery.INTERNATIONAL || delivery == TypeDelivery.STANDARD;
    }

    private Boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
