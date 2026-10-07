package com.example.cafesystem.domain.charge.service;

import com.example.cafesystem.domain.charge.dto.CreateChargeRequest;
import com.example.cafesystem.domain.charge.entity.Charge;
import com.example.cafesystem.domain.charge.repository.ChargeRepository;
import com.example.cafesystem.domain.user.entity.User;
import com.example.cafesystem.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ActiveProfiles("test")
public class ChargeConcurrencyTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private ChargeService chargeService;

    @Test
    void 동시에_여러번_충전해도_모든_충전금액이_정상_반영된다() throws Exception {
        // given
        User user = new User("채원");
        userRepository.saveAndFlush(user);

        int threadCount = 100;
        long chargeAmount = 1000L;

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<?>> futures = new ArrayList<>();

        // when
        for (int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(() ->
                    chargeService.create(
                            user.getId(),
                            new CreateChargeRequest(chargeAmount)
                    )
            ));
        }

        for (Future<?> future : futures) {
            future.get();
        }

        executorService.shutdown();

        // then
        User savedUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(savedUser.getPoint())
                .isEqualTo(threadCount * chargeAmount);
    }
}
