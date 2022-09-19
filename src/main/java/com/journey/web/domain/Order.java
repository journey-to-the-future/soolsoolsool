package com.journey.web.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private Member member;

    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrderStatus status;
}
