package com.example.cafesystem.domain.menu.service;


import com.example.cafesystem.domain.menu.dto.GetMenusResponse;
import com.example.cafesystem.domain.menu.entity.Menu;
import com.example.cafesystem.domain.menu.repository.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class MenuIntegrationTest {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("메뉴 전체 조회 통합테스트")
    void findAll_통합테스트() {
        // given
        List<Menu> menuList = menuRepository.findAll();

        // when
        List<GetMenusResponse> responses = menuService.findAll();

        // then
        assertThat(responses.size()).isEqualTo(menuList.size());
        assertThat(responses.get(0).id()).isEqualTo(menuList.get(0).getId());
        assertThat(responses.get(0).name()).isEqualTo(menuList.get(0).getName());
    }

    @Test
    @DisplayName("GET /api/menus - 전체 메뉴 조회")
    void 메뉴_전체조회_통합테스트() throws Exception {

        mockMvc.perform(get("/api/menus"))
                .andExpect(status().isOk());

    }
}
