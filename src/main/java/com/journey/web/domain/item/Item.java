package com.journey.web.domain.item;

import com.journey.web.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;        // 상품 이름
    private int price;          // 가격
    private String info;        // 상품 설명
    private double degree;      // 도수
    private int size;           // 규격, 사이즈(ml)
    private String company;     // 제조사
    private String material;    // 주원료
    private String soolType;    // 주종(탁주 T, 소주 S, 와인 W, 청주 C)
    private int stockQuantity;  // 재고
    private boolean isSoldout;  // 판매여부 (품절 : 0, 기본 : 1)
    private String imageUrl;    // 이미지 파일 url
    private Long creatorId;     // 상품 등록한 ID

//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();

    public static Item createItem(String name, int price, String info, double degree, int size,
                       String company, String material, String soolType, int stockQuantity,
                       boolean isSoldout, String imageUrl, Long creatorId) {
        Item item = Item.builder()
                .name(name)
                .price(price)
                .info(info)
                .degree(degree)
                .size(size)
                .company(company)
                .material(material)
                .soolType(soolType)
                .stockQuantity(stockQuantity)
                .creatorId(creatorId)
                .build();

        return item;
    }

}
