package com.example.cafesystem.domain.menu.repository;

import com.example.cafesystem.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
