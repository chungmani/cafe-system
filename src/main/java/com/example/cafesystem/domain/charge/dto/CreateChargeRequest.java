package com.example.cafesystem.domain.charge.dto;

import jakarta.validation.constraints.Min;

public record CreateChargeRequest(

        @Min(1)
        long amount
) {
}
