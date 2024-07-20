package org.example.orders.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.example.orders.models.dto.request.OrderRequest;
import org.example.orders.models.dto.response.OrderResponse;
import org.example.orders.models.dto.request.CreateOrderRequest;
import org.example.orders.service.NumberService;
import org.example.orders.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/orders/")
public class OrderController {

    private final OrderService orderService;
    private final NumberService numberService;

    public OrderController(OrderService orderService, NumberService numberService) {
        this.orderService = orderService;
        this.numberService = numberService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getOrderById() {
        String number = numberService.getNumber();
        return new ResponseEntity<>(number, HttpStatus.OK);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @Parameter(name = "order_id", required = true)
            @PathVariable("order_id") Long order_id)
    {
        OrderResponse orderResponse = orderService.getOrderById(order_id);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderResponse>> getOrdersByDateAndBySum(
            @RequestParam String orderDate,
            @RequestParam Long price,
            @RequestParam Long precision)
    {
        List<OrderResponse> orders = orderService.getOrders(orderDate, price, precision);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @PostMapping("/search_without_order")
    public ResponseEntity<List<OrderResponse>> getOrdersByDateAndBySum(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date orderDate,
            @RequestBody OrderRequest orderRequest)
    {
        List<OrderResponse> orders = orderService.getOrdersWithoutOrder(orderDate, orderRequest);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
