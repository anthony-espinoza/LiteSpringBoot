package com.litethinking.orders.api.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.litethinking.orders.api.dto.CreateOrderRequest;
import com.litethinking.orders.api.dto.OrderResponse;

@Service
public class OrderService {

    public OrderResponse create(CreateOrderRequest req) {
        return new OrderResponse(
                UUID.randomUUID().toString(),
                req.customerEmail(),
                req.items(),
                req.total(),
                "CREATED",
                Instant.now());
    }

    public OrderResponse getById(String id) {
        // placeholder, luego lo reemplazamos con DB
        throw new IllegalArgumentException("NOT_IMPLEMENTED");
    }
}