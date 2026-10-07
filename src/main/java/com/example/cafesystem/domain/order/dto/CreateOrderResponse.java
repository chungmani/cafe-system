package com.example.cafesystem.domain.order.dto;

import com.example.cafesystem.domain.order.entity.Order;
import com.example.cafesystem.domain.order.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOrderResponse(
        Long orderId,
        Long userId,
        long totalPrice,
        List<OrderItemResponse> responses,
        LocalDateTime orderedAt
) {
    public static CreateOrderResponse from(Order order, List<OrderItemResponse> items) {
        return new CreateOrderResponse(
                order.getId(),
                order.getUser().getId(),
                order.getTotalPrice(),
                items,
                order.getCreatedAt()
        );
    }
}
