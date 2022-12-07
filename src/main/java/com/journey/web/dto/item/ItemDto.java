package com.journey.web.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {

    private Long itemId;
    private String name;
    private int price;
    private String info;        // 상품 설명
    private double degree;      // 도수
    private int size;           // 규격, 사이즈(ml)
    private String company;     // 제조사
    private String material;    // 주원료
    private String soolType;    // 주종(탁주 T, 소주 S, 와인 W, 청주 C)
    private int stockQuantity;  // 재고
    private String imageUrl;    // 이미지 파일 url
}
