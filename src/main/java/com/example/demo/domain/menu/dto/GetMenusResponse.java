package com.example.demo.domain.menu.dto;

import com.example.demo.domain.menu.entity.Menu;

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
