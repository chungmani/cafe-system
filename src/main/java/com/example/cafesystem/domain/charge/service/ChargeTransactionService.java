package com.example.cafesystem.domain.charge.service;

import com.example.cafesystem.domain.charge.dto.CreateChargeRequest;
import com.example.cafesystem.domain.charge.dto.CreateChargeResponse;
import com.example.cafesystem.domain.charge.entity.Charge;
import com.example.cafesystem.domain.charge.repository.ChargeRepository;
import com.example.cafesystem.domain.user.entity.User;
import com.example.cafesystem.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChargeTransactionService {

    private final ChargeRepository chargeRepository;
    private final UserService userService;

    @Transactional
    public CreateChargeResponse charge(Long userId, CreateChargeRequest request) {
        User user = userService.getUser(userId);
        Charge charge = new Charge(request.amount(), user);
        userService.chargePoint(userId, request.amount());
        Charge savedCharge = chargeRepository.save(charge);
        User updatedUser = userService.getUser(userId);
        return CreateChargeResponse.from(savedCharge, updatedUser);
    }
}
