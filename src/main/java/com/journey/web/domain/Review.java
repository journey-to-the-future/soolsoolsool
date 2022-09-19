package com.journey.web.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private Member member;

    private Item item;

    private LocalDateTime date;
    private String review;
    private int point;
    private int star;
}
