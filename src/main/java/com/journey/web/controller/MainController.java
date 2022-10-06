package com.journey.web.controller;

import com.journey.web.domain.item.Item;
import com.journey.web.dto.item.ItemsDto;
import com.journey.web.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    // 비로그인 유저
    @GetMapping("/")
    public Result items() {

        List<Item> findItems = itemService.findItems();
        List<ItemsDto> collect = findItems.stream()
                .map(i -> new ItemsDto(i.getName(), i.getPrice(), i.getCompany(), i.getSoolType(), i.getStockQuantity(), i.getImageUrl()))
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
