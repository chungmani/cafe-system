package com.example.cafesystem.domain.user.repository;

import com.example.cafesystem.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    // 포인트 충전 원자적 업데이트 쿼리문
    @Modifying(clearAutomatically = true)
    @Query("""
    UPDATE User u SET u.point = u.point + :amount
    WHERE u.id = :userId
    """)
    int updateChargePoint(@Param("userId") Long userId, @Param("amount") long amount);
}
