package com.example.cafesystem.domain.charge.service;

import com.example.cafesystem.domain.charge.dto.CreateChargeRequest;
import com.example.cafesystem.domain.charge.dto.CreateChargeResponse;
import com.example.cafesystem.domain.charge.entity.Charge;
import com.example.cafesystem.domain.charge.repository.ChargeRepository;
import com.example.cafesystem.domain.user.entity.User;
import com.example.cafesystem.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ChargeIntegrationTest {

    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private ChargeService chargeService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void create_정상테스트() {
        // given
        User user = new User("채원");
        userRepository.save(user);
        CreateChargeRequest request = new CreateChargeRequest(10000);
        long beforePoint = user.getPoint();

        // when
        CreateChargeResponse response = chargeService.create(user.getId(), request);

        // then
        assertThat(response.currentPoint()).isEqualTo(request.amount() + beforePoint);

        Charge charge = chargeRepository.findById(response.id()).orElseThrow(
                () -> new RuntimeException("충전기록이 없습니다.")
        );
        assertThat(charge.getId()).isEqualTo(response.id());
        assertThat(response.chargedPoint()).isEqualTo(charge.getPoint());
    }
}
