package com.journey.web.dto.item;

import com.journey.web.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ItemListDto {

    private Long id;
    private String name;
    private int price;
    private double degree;      // 도수
    private int size;           // 규격, 사이즈(ml)
    private String company;     // 제조사
    private String material;    // 주원료
    private String soolType;    // 주종(탁주 T, 소주 S, 와인 W, 청주 C)
    private int stockQuantity;  // 재고
    private String imageUrl;    // 이미지 파일 url

}
