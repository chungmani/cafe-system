package com.example.cafesystem.domain.charge.service;

import com.example.cafesystem.domain.charge.dto.CreateChargeRequest;
import com.example.cafesystem.domain.charge.dto.CreateChargeResponse;
import com.example.cafesystem.domain.charge.entity.Charge;
import com.example.cafesystem.domain.charge.repository.ChargeRepository;
import com.example.cafesystem.domain.user.entity.User;
import com.example.cafesystem.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChargeService {

    private final ChargeRepository chargeRepository;
    private final UserService userService;
    private final ChargeTransactionService transactionService;

    // 포인트 충전
    public CreateChargeResponse create(Long userId, CreateChargeRequest request) {
        int maxRetry = 3;

        for (int attempt = 1; attempt <= maxRetry; attempt++) {
            try {
                return transactionService.charge(userId, request);
            } catch (CannotAcquireLockException e) {
                if (attempt == maxRetry) {
                    throw e;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();;
                    throw new RuntimeException(ex);
                }
            }
        }
        throw new IllegalArgumentException("포인트 충전에 실패했습니다.");
    }

}
