package com.litethinking.orders.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CreateOrderRequest(
        @NotNull @Email String customerEmail,
        @NotEmpty List<@NotEmpty String> items,
        @NotNull @Positive Double total
) {}