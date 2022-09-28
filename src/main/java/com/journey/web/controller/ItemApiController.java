package com.journey.web.controller;

import com.journey.web.domain.item.Item;
import com.journey.web.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/item/{id}")
    public Result item(@PathVariable("id") Long id) {

        Item findItem = itemService.findOne(id);
        return new Result(findItem);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
