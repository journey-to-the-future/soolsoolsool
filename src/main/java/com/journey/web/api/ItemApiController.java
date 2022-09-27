package com.journey.web.api;

import com.journey.web.domain.item.Item;
import com.journey.web.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/api/main/{item}")
    public Result items() {

        List<Item> findItems = itemService.findItems();
        List<ItemDto> collect = findItems.stream()
                .map(i -> new ItemDto(i.getName(), i.getPrice(), i.getInfo(), i.getDegree(), i.getSize(), i.getCompany(), i.getMaterial(), i.getType(), i.getStockQuantity(), i.getImage_url()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ItemDto {

        private String name;
        private int price;
        private String info;        // 상품 설명
        private double degree;      // 도수
        private int size;           // 규격, 사이즈(ml)
        private String company;     // 제조사
        private String material;    // 주원료
        private String type;        // 주종(탁주 T, 소주 S, 와인 W, 청주 C)
        private int stockQuantity;  // 재고
        private String image_url;   // 이미지 파일 url
    }

}
