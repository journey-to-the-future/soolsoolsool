package com.journey.web.domain.item;

import com.journey.web.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;        // 상품 이름
    private int price;          // 가격
    private String info;        // 상품 설명
    private double degree;      // 도수
    private int size;           // 규격, 사이즈(ml)
    private String company;     // 제조사
    private String material;    // 주원료
    private String type;        // 주종(탁주 T, 소주 S, 와인 W, 청주 C)
    private int stockQuantity;  // 재고
    private boolean isSoldout;  // 판매여부
    private String image_url;   // 이미지 파일 url

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
