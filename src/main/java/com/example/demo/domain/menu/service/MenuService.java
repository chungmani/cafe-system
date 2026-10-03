package com.example.demo.domain.menu.service;

import com.example.demo.domain.menu.dto.GetMenusResponse;
import com.example.demo.domain.menu.entity.Menu;
import com.example.demo.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    // 커피 메뉴 목록 조회
    public List<GetMenusResponse> findAll() {
        List<Menu> menus = menuRepository.findAll();

        return menus.stream()
                .map(GetMenusResponse::from)
                .toList();
    }
}
