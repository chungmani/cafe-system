package com.example.cafesystem.domain.user.service;

import com.example.cafesystem.domain.user.entity.User;
import com.example.cafesystem.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("없는 유저입니다.")
        );
    }

    // 포인트 추가
    public void chargePoint(Long userId, long amount) {
        int success = userRepository.updateChargePoint(userId, amount);
        if (success < 1) {
            throw new RuntimeException("포인트 충전에 실패했습니다.");
        }
    }
}
