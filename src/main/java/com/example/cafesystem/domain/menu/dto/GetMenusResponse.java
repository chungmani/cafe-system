package com.example.cafesystem.domain.menu.dto;

import com.example.cafesystem.domain.menu.entity.Menu;

public record GetMenusResponse(
        Long id,
        String name,
        long price
) {
    public static GetMenusResponse from(Menu menu) {
        return new GetMenusResponse(
                menu.getId(), menu.getName(), menu.getPrice()
        );
    }
}
