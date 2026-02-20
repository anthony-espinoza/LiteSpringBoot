package com.litethinking.orders.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.litethinking.orders.api.dto.CreateOrderRequest;
import com.litethinking.orders.api.dto.OrderResponse;
import com.litethinking.orders.error.NotFoundException;
import com.litethinking.orders.repository.OrderRepository;
import com.litethinking.orders.repository.entity.OrderEntity;
import com.litethinking.orders.repository.entity.OrderItemEntity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public OrderResponse create(CreateOrderRequest req) {
        String id = UUID.randomUUID().toString();
        OrderEntity order = new OrderEntity(id, req.customerEmail(), req.total(), "CREATED", Instant.now());
        req.items().forEach(name -> order.addItem(new OrderItemEntity(name)));
        repo.save(order);
        return toResponse(order);
    }

    @Transactional(readOnly = true)
    public OrderResponse getById(String id) {
        OrderEntity order = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found: " + id));
        return toResponse(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findByCustomerEmail(String email) {
        return repo.findByCustomerEmail(email).stream().map(this::toResponse).toList();
    }

    private OrderResponse toResponse(OrderEntity e) {
        List<String> items = e.getItems().stream().map(OrderItemEntity::getName).toList();
        return new OrderResponse(
                e.getId(),
                e.getCustomerEmail(),
                items,
                e.getTotal(),
                e.getStatus(),
                e.getCreatedAt());
    }
}
