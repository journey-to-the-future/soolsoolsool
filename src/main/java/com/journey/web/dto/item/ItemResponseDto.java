package com.journey.web.dto.item;

import com.journey.web.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemResponseDto {

    private Long id;
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


    public ItemResponseDto(Item entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.info = entity.getInfo();
        this.degree = entity.getDegree();
        this.size = entity.getSize();
        this.company = entity.getCompany();
        this.material = entity.getMaterial();
        this.soolType = entity.getSoolType();
        this.stockQuantity = entity.getStockQuantity();
        this.imageUrl = entity.getImageUrl();
    }

}
