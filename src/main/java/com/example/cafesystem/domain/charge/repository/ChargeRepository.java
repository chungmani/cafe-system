package com.example.cafesystem.domain.charge.repository;

import com.example.cafesystem.domain.charge.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
}
