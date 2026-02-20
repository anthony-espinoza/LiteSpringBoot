package com.litethinking.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litethinking.orders.repository.entity.OrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByCustomerEmail(String customerEmail);
}
