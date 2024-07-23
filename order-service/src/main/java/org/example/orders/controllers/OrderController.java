package org.example.orders.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.example.orders.models.dto.request.GetOrdersWithoutOrderBetweenDatesRequest;
import org.example.orders.models.dto.response.ListOrdersResponse;
import org.example.orders.models.dto.response.OrderResponse;
import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.models.dto.request.GetOrdersByDateAnsSumRequest;
import org.example.orders.service.NumberService;
import org.example.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiKeys.ORDERS)
public class OrderController {

    private final OrderService orderService;
    private final NumberService numberService;

    public OrderController(OrderService orderService, NumberService numberService) {
        this.orderService = orderService;
        this.numberService = numberService;
    }

    @GetMapping("/")
    public ResponseEntity<String> g() {
        String response = numberService.getNumber();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(ApiKeys.PATH_ID)
    public ResponseEntity<OrderResponse> getById(@Parameter(name = "id", required = true)  @PathVariable("id") Long id) {
        OrderResponse response = orderService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(ApiKeys.PATH_CREATE)
    public ResponseEntity<Void> create(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.create(createOrderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(ApiKeys.PATH_GET_BY_DATE_AND_SUM)
    public ResponseEntity<ListOrdersResponse> getByDateAndSum(@RequestBody GetOrdersByDateAnsSumRequest getOrdersRequest) {
        ListOrdersResponse response = orderService.getByDateAnsSum(getOrdersRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(ApiKeys.PATH_GET_WITHOUT_ORDER_BETWEEN_DATES)
    public ResponseEntity<ListOrdersResponse> getWithoutOrderBetweenDates(@RequestBody GetOrdersWithoutOrderBetweenDatesRequest getOrdersRequest) {
        ListOrdersResponse response = orderService.getWithoutOrderAndBetweenDates(getOrdersRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
