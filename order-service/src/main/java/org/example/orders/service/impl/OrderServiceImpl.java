package org.example.orders.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.orders.exceptions.NoUniqueNumbersLeftException;
import org.example.orders.exceptions.NotValidOrderException;
import org.example.orders.exceptions.NotValidParametersException;
import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.GetOrdersByDateAnsSumRequest;
import org.example.orders.models.dto.request.GetOrdersWithoutOrderBetweenDatesRequest;
import org.example.orders.models.dto.response.ListOrdersResponse;
import org.example.orders.models.dto.response.OrderResponse;
import org.example.orders.models.entity.OrderEntity;
import org.example.orders.models.mapper.OrderMapper;
import org.example.orders.repository.OrderRepository;
import org.example.orders.service.NumberService;
import org.example.orders.service.OrderService;
import org.example.orders.service.OrderValidatorService;
import org.example.orders.utils.DateParse;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final NumberService numberService;
    private final OrderValidatorService validator;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final DateParse dateParse;

    public OrderServiceImpl(NumberService numberService,
                            OrderValidatorService orderValidatorService,
                            OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            DateParse dateParse
    ) {
        this.numberService = numberService;
        this.validator = orderValidatorService;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.dateParse = dateParse;
    }

    @Override
    public void create(CreateOrderRequest createOrderRequest) throws NotValidOrderException, NoUniqueNumbersLeftException {
        if (!validator.isValidCreateOrderRequest(createOrderRequest)){
            log.error("Invalid request for create order");
            throw new NotValidOrderException("Invalid create order request");
        }
        String number = numberService.getNumber();
        log.info("Get number: {}", number);
        if (isError(number)){
            log.error("No left number in generate number service");
            throw new NoUniqueNumbersLeftException(numberService.ERROR);
        }
        OrderEntity order = orderMapper.toEntity(createOrderRequest);
        order.setNumber(number);
        orderRepository.save(order);
        log.info("Successfully created order with number {}", number);
    }

    @Override
    public OrderResponse getById(Integer id){
        return orderMapper.toResponse(orderRepository.findById(id));
    }

    @Override
    public ListOrdersResponse getByDateAnsSum(GetOrdersByDateAnsSumRequest getOrdersRequest) {
        if (!validator.isValidGetOrdersByDateAnsSumRequest(getOrdersRequest)){
            log.error("Invalid request for get orders by date and sum");
            throw new NotValidParametersException("Parameters is not valid");
        }
        log.info("Valid request for get orders by date and sum");
        List<OrderEntity> listOrderEntity = orderRepository.findByDateAndSum(
                dateParse.getDateFromString(getOrdersRequest.getCurrentDate()),
                getOrdersRequest.getSum(),
                getOrdersRequest.getScale()
        );

        log.info("Successfully get orders by date and sum");
        return orderMapper.toListResponse(listOrderEntity);
    }

    @Override
    public ListOrdersResponse getWithoutOrderAndBetweenDates(GetOrdersWithoutOrderBetweenDatesRequest getOrdersRequest) {
        if(!validator.isValidGetOrdersWithoutOrderBetweenDatesRequest(getOrdersRequest)){
            log.error("Invalid request for get orders without order");
            throw new NotValidParametersException("Parameters is not valid");
        }
        log.info("Valid request for get orders without order");
        List<OrderEntity> listOrderEntity = orderRepository.findWithoutOrderAndBetweenDate(
                getOrdersRequest.getNumber(),
                dateParse.getDateFromString(getOrdersRequest.getDateBefore()),
                dateParse.getDateFromString(getOrdersRequest.getDateAfter())
        );
        log.info("Successfully get orders without order and between dates");
        return orderMapper.toListResponse(listOrderEntity);
    }

    private Boolean isError(String message){
        return message.equals(numberService.ERROR);
    }
}
