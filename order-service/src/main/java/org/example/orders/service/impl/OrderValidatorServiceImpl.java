package org.example.orders.service.impl;

import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.repository.ItemRepository;
import org.example.orders.service.OrderValidatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class OrderValidatorServiceImpl implements OrderValidatorService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    private final ItemRepository itemRepository;

    public OrderValidatorServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Boolean isValidCreateOrderRequest(CreateOrderRequest createOrderRequest){
        return isValidItemId(createOrderRequest.getItemId())
                && isValidSum(createOrderRequest.getSum())
                && isValidScale(createOrderRequest.getScale())
                && isValidAdditionalInfo(createOrderRequest);
    }



    public Boolean isValidOrderRequest(GetWithoutOrderRequest orderRequest){
        return true;
    }

    public Boolean isValidSum(Long sum){
        return sum != 0;
    }

    public Boolean isValidScale(Long scale){
        return scale >= 0;
    }

    private Boolean isValidItemId(Long itemId){
        return itemRepository.isExistItemById(itemId);
    }

    private Boolean isValidAdditionalInfo(CreateOrderRequest createOrderRequest){
        return isValidAddress(createOrderRequest.getAddress())
                && isValidRecipient(createOrderRequest.getRecipient())
                && isValidPayment(createOrderRequest.getPayment())
                && isValidDelivery(createOrderRequest.getDelivery());
    }

    private Boolean isValidAddress(String address){
        return !address.isBlank() && !address.isEmpty() && address.length() < 256;
    }

    private Boolean isValidRecipient(String recipient){
        return !recipient.isBlank() && !recipient.isEmpty() && recipient.length() < 256;
    }

    private Boolean isValidPayment(String payment){
        return !payment.isBlank() && !payment.isEmpty() && payment.length() < 256;
    }

    private Boolean isValidDelivery(String delivery){
        return !delivery.isBlank() && !delivery.isEmpty() && delivery.length() < 256;
    }


    public Boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
