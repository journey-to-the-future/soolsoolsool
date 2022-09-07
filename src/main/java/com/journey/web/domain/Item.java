package com.journey.web.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private double degree;  // 도수
    private int size;       // 규격, 사이즈
    private String company; // 제조사

//    private List<> = new ArrayList<>(); // 주원료
}
