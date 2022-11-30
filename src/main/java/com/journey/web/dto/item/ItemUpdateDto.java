package com.journey.web.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemUpdateDto {

    private Long itemId;
    private String updateName;
    private int updatePrice;
    private String updateInfo;  // 상품 설명 업데이트
    private String updateMaterial;    // 주원료 업데이트
}
