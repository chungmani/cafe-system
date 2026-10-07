package com.example.cafesystem.domain.order.service;

import com.example.cafesystem.domain.order.dto.CreateOrderRequest;
import com.example.cafesystem.domain.order.dto.CreateOrderResponse;
import com.example.cafesystem.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
}
