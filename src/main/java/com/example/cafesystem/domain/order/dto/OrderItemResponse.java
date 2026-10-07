package com.example.cafesystem.domain.order.dto;

import com.example.cafesystem.domain.order.entity.OrderItem;

public record OrderItemResponse(
        Long menuId,
        String menuName,
        long menuPrice,
        int quantity
) {
    public static OrderItemResponse from(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getMenu().getId(),
                orderItem.getMenuName(),
                orderItem.getMenuPrice(),
                orderItem.getQuantity()
        );
    }
}
