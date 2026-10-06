package com.example.cafesystem.domain.menu.controller;

import com.example.cafesystem.domain.menu.dto.GetMenusResponse;
import com.example.cafesystem.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    // 커피 메뉴 목록 조회
    @GetMapping
    public ResponseEntity<List<GetMenusResponse>> getAll() {
        return ResponseEntity.ok(menuService.findAll());
    }

}
