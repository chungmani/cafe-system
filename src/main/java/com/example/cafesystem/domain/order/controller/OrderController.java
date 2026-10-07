package com.example.cafesystem.domain.order.controller;

import com.example.cafesystem.domain.order.dto.CreateOrderRequest;
import com.example.cafesystem.domain.order.dto.CreateOrderResponse;
import com.example.cafesystem.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
}
