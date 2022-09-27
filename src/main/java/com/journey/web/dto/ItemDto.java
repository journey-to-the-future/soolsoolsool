package com.journey.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {

    private String name;
    private int price;
    private String company;     // 제조사
    private String type;        // 주종(탁주 T, 소주 S, 와인 W, 청주 C)
    private int stockQuantity;  // 재고
    private String image_url;
}
