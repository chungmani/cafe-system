package com.example.cafesystem.domain.charge.service;

import com.example.cafesystem.domain.charge.dto.CreateChargeRequest;
import com.example.cafesystem.domain.charge.repository.ChargeRepository;
import com.example.cafesystem.domain.user.entity.User;
import com.example.cafesystem.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ChargeServiceTest {

    @Mock
    private ChargeRepository chargeRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChargeService chargeService;

    @Test
    void 충전금액이_0일때_충전실패() {
        // given
        User user = new User("채원");
        when(userService.getUser(1L)).thenReturn(user);
        CreateChargeRequest request = new CreateChargeRequest(0);

        // when & then
        assertThatThrownBy(() -> chargeService.create(1L, request))
                .isInstanceOf(RuntimeException.class);

    }

    @Test
    void 충전금액이_음수일때_충전실패() {
        // given
        User user = new User("채원");
        when(userService.getUser(1L)).thenReturn(user);
        CreateChargeRequest request = new CreateChargeRequest(-10);

        // when & then
        assertThatThrownBy(() -> chargeService.create(1L, request))
                .isInstanceOf(RuntimeException.class);
    }

}