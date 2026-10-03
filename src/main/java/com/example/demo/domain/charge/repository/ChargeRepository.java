package com.example.demo.domain.charge.repository;

import com.example.demo.domain.charge.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
}
