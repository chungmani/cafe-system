package com.example.cafesystem.domain.charge.dto;

import com.example.cafesystem.domain.charge.entity.Charge;

import java.time.LocalDateTime;

public record CreateChargeResponse(
        Long id,
        long chargedPoint,
        long currentPoint,
        LocalDateTime chargedAt
) {
    public static CreateChargeResponse from(Charge charge) {
        return new CreateChargeResponse(
                charge.getId(),
                charge.getPoint(),
                charge.getUser().getPoint(),
                charge.getChargedAt()
        );
    }
}
