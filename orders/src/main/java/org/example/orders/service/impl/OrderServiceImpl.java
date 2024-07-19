package org.example.orders.service.impl;

import org.example.orders.exceptions.NoUniqueNumbersLeftException;
import org.example.orders.exceptions.NotValidOrderException;
import org.example.orders.exceptions.NotValidParametersException;
import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.OrderRequest;
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

    public void createOrder(CreateOrderRequest createOrderRequest) throws NotValidOrderException, NoUniqueNumbersLeftException {
        if (!validator.isValidCreateOrderRequest(createOrderRequest)){
            throw new NotValidOrderException("Invalid create order request");
        }
        String number = numberService.getNumber();
        if (isNotValidNumber(number)){
            throw new NoUniqueNumbersLeftException("No unique numbers left");
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

    public List<OrderResponse> getOrders(String orderDate, Long sum, Long scale){
        if (validator.isValidScale(scale) && validator.isValidSum(sum) && validator.isValidDate(orderDate)){
            Date date = dateParse.getDateFromString(orderDate);
            Optional<List<BaseOrderEntity>> optionalOrders = orderRepository.getOrderByDateAndSum("orders", date, sum, scale);
            if (optionalOrders.isPresent()){
                List<BaseOrderEntity> orders= optionalOrders.get();

            }
        }
        throw new NotValidParametersException("parameters not valid");
    }

    public List<OrderResponse> getOrdersWithoutOrder(Date orderDate, OrderRequest orderRequest) {
        return null;
    }

    private Boolean isNotValidNumber(String number){
        return number.equals("1");
    }
}
