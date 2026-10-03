package com.example.demo.domain.charge.entity;

import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "charges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long point;

    @Column(name = "charged_at")
    private LocalDateTime chargedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Charge(long point, User user) {
        if (point < 1) {
            throw new RuntimeException("포인트 충전은 1 이상이어야 합니다.");
        }

        this.point = point;
        this.user = user;
    }
}
