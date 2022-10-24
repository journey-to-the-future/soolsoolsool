package com.journey.web.controller;

import com.journey.web.domain.item.Item;
import com.journey.web.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/item")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ApiOperation(value = "상품 정보 조회", notes = "itemId를 이용하여 상품 정보를 조회")
    @GetMapping("/{id}")
    public Result item(@PathVariable("id") Long id) {

        Item findItem = itemService.findOne(id);
        return new Result(findItem);
    }

    @ApiOperation(value = "상품 리스트", notes = "전체 상품 조회")
    @GetMapping("/list")
    public String list(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page) {

        Page<Item> listPage = itemService.list(page);

        int totalPage = listPage.getTotalPages();

        model.addAttribute("item", listPage.getContent());
        model.addAttribute("totalPage", totalPage);

        return "list";
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
