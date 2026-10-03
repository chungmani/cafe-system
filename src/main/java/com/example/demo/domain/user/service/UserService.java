package com.example.demo.domain.user.service;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
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
    public void chargePoint(User user, long amount) {
        user.updatePoint(amount);
    }
}
