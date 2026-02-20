package com.litethinking.orders.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.litethinking.orders.api.dto.CreateOrderRequest;
import com.litethinking.orders.api.dto.OrderResponse;
import com.litethinking.orders.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping
    public List<OrderResponse> find(@RequestParam(required = false) String customerEmail) {
        if (customerEmail == null || customerEmail.isBlank())
            return List.of();
        return service.findByCustomerEmail(customerEmail);
    }
}
