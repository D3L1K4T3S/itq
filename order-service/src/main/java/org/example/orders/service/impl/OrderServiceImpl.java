package org.example.orders.service.impl;

import org.example.orders.exceptions.NoUniqueNumbersLeftException;
import org.example.orders.exceptions.NotValidOrderException;
import org.example.orders.exceptions.NotValidParametersException;
import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.GetOrdersRequest;
import org.example.orders.models.dto.response.ListOrdersResponse;
import org.example.orders.models.dto.response.OrderResponse;
import org.example.orders.models.entity.base.BaseOrderEntity;
import org.example.orders.repository.OrderRepository;
import org.example.orders.service.NumberService;
import org.example.orders.service.OrderService;
import org.example.orders.service.OrderValidatorService;
import org.example.orders.utils.DateParse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final NumberService numberService;
    private final OrderValidatorService validator;
    private final OrderRepository orderRepository;
    private final DateParse dateParse;

    public OrderServiceImpl(NumberService numberService,
                            OrderValidatorService orderValidatorService,
                            OrderRepository orderRepository,
                            DateParse dateParse
    ) {
        this.numberService = numberService;
        this.validator = orderValidatorService;
        this.orderRepository = orderRepository;
        this.dateParse = dateParse;
    }

    public void create(CreateOrderRequest createOrderRequest) throws NotValidOrderException, NoUniqueNumbersLeftException {
        if (!validator.isValidCreateOrderRequest(createOrderRequest)){
            throw new NotValidOrderException("Invalid create order request");
        }
        String number = numberService.getNumber();
        if (isError(number)){
            throw new NoUniqueNumbersLeftException(numberService.ERROR);
        }
        orderRepository.saveOrder(
                createOrderRequest.getSum(),
                createOrderRequest.getScale(),
                createOrderRequest.getRecipient(),
                createOrderRequest.getAddress(),
                createOrderRequest.getPayment(),
                createOrderRequest.getDelivery(),
                createOrderRequest.getItemId(),
                number
        );
    }

    public OrderResponse getOrderById(Long id){
        Optional<BaseOrderEntity> optionalOrderEntity = orderRepository.getOrderById("orders", id);
        if (optionalOrderEntity.isPresent()){
            BaseOrderEntity orderEntity = optionalOrderEntity.get();
            return new OrderResponse(orderEntity.getId(), orderEntity.getNumber(), orderEntity.getSum(),
                    orderEntity.getScale(), orderEntity.getOrderDate(), orderEntity.getRecipient(),
                    orderEntity.getAddress(), orderEntity.getPayment(), orderEntity.getDelivery()
            );
        }
        throw new NotValidOrderException("Order not found");
    }

    public ListOrdersResponse get(GetOrdersRequest getOrdersRequest){
        throw new NotValidParametersException("Parameters is not valid");
    }

    private Boolean isError(String message){
        return message.equals(numberService.ERROR);
    }
}
