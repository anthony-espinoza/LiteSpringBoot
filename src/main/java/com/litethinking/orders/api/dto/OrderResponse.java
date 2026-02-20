package com.litethinking.orders.api.dto;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        String customerEmail,
        List<String> items,
        Double total,
        String status,
        Instant createdAt
) {}