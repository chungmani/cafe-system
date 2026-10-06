package com.example.cafesystem.domain.menu.service;

import com.example.cafesystem.domain.menu.dto.GetMenusResponse;
import com.example.cafesystem.domain.menu.entity.Menu;
import com.example.cafesystem.domain.menu.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    void 정상적인_메뉴_조회() {
        // given
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("커피1", 1000, 10));
        menus.add(new Menu("커피2", 2000, 10));
        menus.add(new Menu("커피3", 3000, 10));
        when(menuRepository.findAll()).thenReturn(menus);

        // when
        List<GetMenusResponse> responses = menuService.findAll();

        // then
        assertThat(menus.size()).isEqualTo(responses.size());
        assertThat(menus.get(0).getId()).isEqualTo(responses.get(0).id());
        assertThat(menus.get(1).getId()).isEqualTo(responses.get(1).id());
        assertThat(menus.get(2).getId()).isEqualTo(responses.get(2).id());
        assertThat(menus.get(0).getName()).isEqualTo(responses.get(0).name());
        assertThat(menus.get(1).getName()).isEqualTo(responses.get(1).name());
        assertThat(menus.get(2).getName()).isEqualTo(responses.get(2).name());
        assertThat(menus.get(0).getPrice()).isEqualTo(responses.get(0).price());
        assertThat(menus.get(1).getPrice()).isEqualTo(responses.get(1).price());
        assertThat(menus.get(2).getPrice()).isEqualTo(responses.get(2).price());
    }

    @Test
    void 메뉴가_존재하지_않음() {
        // given
        List<Menu> menus = new ArrayList<>();
        when(menuRepository.findAll()).thenReturn(menus);

        // when
        List<GetMenusResponse> responses = menuService.findAll();

        // then
        assertThat(responses).isEmpty();
    }
}