package com.example.cafesystem.domain.menu.entity;

import com.example.cafesystem.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "menus")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private long stock;

    public Menu(String name, long price, long stock) {
        if (price < 0) {
            throw new RuntimeException("가격은 0 이상이어야 합니다.");
        }

        if (stock < 0) {
            throw new RuntimeException("재고는 0 이상이어야 합니다.");
        }

        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
