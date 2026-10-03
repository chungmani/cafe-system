package com.example.demo.domain.charge.service;

import com.example.demo.domain.charge.dto.CreateChargeRequest;
import com.example.demo.domain.charge.dto.CreateChargeResponse;
import com.example.demo.domain.charge.entity.Charge;
import com.example.demo.domain.charge.repository.ChargeRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChargeService {

    private final ChargeRepository chargeRepository;
    private final UserService userService;

    @Transactional
    public CreateChargeResponse create(Long userId, @Valid CreateChargeRequest request) {

        User user = userService.getUser(userId);

        Charge charge = new Charge(request.amount(), user);
        Charge savedCharge = chargeRepository.save(charge);
        userService.chargePoint(user, request.amount());

        return CreateChargeResponse.from(savedCharge);
    }
}
