package com.journey.web.api;

import com.journey.web.domain.item.Item;
import com.journey.web.dto.ItemDto;
import com.journey.web.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ShopApiController {

    private final ItemService itemService;

    // 비로그인 유저
    @GetMapping("/")
    public Result items() {

        List<Item> findItems = itemService.findItems();
        List<ItemDto> collect = findItems.stream()
                .map(i -> new ItemDto(i.getName(), i.getPrice(), i.getCompany(), i.getType(), i.getStockQuantity(), i.getImage_url()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    // 로그인 유저
//    @GetMapping("/main")


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
