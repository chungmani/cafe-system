package com.example.cafesystem.domain.charge.dto;

import com.example.cafesystem.domain.charge.entity.Charge;
import com.example.cafesystem.domain.user.entity.User;

import java.time.LocalDateTime;

public record CreateChargeResponse(
        Long id,
        long chargedPoint,
        long currentPoint,
        LocalDateTime chargedAt
) {
    public static CreateChargeResponse from(Charge charge, User user) {
        return new CreateChargeResponse(
                charge.getId(),
                charge.getPoint(),
                user.getPoint(),
                charge.getChargedAt()
        );
    }
}
