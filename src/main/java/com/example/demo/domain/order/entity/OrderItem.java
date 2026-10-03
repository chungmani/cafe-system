package com.example.demo.domain.order.entity;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.domain.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "orderItems")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private long menuPrice;

    @Column(nullable = false)
    private long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public OrderItem(String menuName, long menuPrice, long quantity, Order order, Menu menu) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.quantity = quantity;
        this.order = order;
        this.menu = menu;
    }
}
