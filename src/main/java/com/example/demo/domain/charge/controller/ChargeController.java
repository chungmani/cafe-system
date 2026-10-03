package com.example.demo.domain.charge.controller;

import com.example.demo.domain.charge.dto.CreateChargeRequest;
import com.example.demo.domain.charge.dto.CreateChargeResponse;
import com.example.demo.domain.charge.service.ChargeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charges")
public class ChargeController {

    private final ChargeService chargeService;

    // 포인트 충전
    @PostMapping("/{userId}")
    public ResponseEntity<CreateChargeResponse> create(
            @PathVariable Long userId,
            @Valid @RequestBody CreateChargeRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chargeService.create(userId, request));
    }
}
