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

    private List<ItemDto> itemDtoList = new ArrayList<>();

    private Long totalPages;

    private Long totalCount;

//    @Builder
//    public ItemListDto(List<Item> itemList, Long totalPages, Long totalCount) {
//        this.itemDtoList = ;
//        this.totalPages = totalPages;
//        this.totalCount = totalCount;
//    }

}
