package com.journey.web.controller;

import com.journey.web.domain.item.Item;
import com.journey.web.dto.item.ItemResponseDto;
import com.journey.web.dto.response.ResponseDto;
import com.journey.web.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/item")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

//    @ApiOperation(value = "상품 정보 조회", notes = "itemId를 이용하여 상품 정보를 조회")
//    @GetMapping("/{id}")
//    public ItemResponseDto findById (@PathVariable("id") Long id) {
//
//        return itemService.findById(id);
//    }

    @ApiOperation(value = "상품 정보 조회", notes = "itemId를 이용하여 상품 정보를 조회")
    @GetMapping("/{id}")
    public ResponseEntity findById (@PathVariable("id") Long id) {

        return new ResponseEntity<ResponseDto>(
                new ResponseDto(200, "success", itemService.findById(id))
        , HttpStatus.OK);
    }

//    @ApiOperation(value = "상품 리스트", notes = "전체 상품 반환")
//    @GetMapping("/list")
//    public String list(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
//
//        Page<Item> listPage = itemService.list(page);
//
//        int totalPage = listPage.getTotalPages();
//
//        model.addAttribute("item", listPage.getContent());
//        model.addAttribute("totalPage", totalPage);
//
//        return "list";
//    }

    @ApiOperation(value = "상품 리스트", notes = "전체 상품 반환")
    @GetMapping("/list")
    public ResponseEntity listItem() {

        return new ResponseEntity<ResponseDto>(
                new ResponseDto(200, "success", itemService.listItem())
        , HttpStatus.OK);
    }

    @ApiOperation(value = "상품 리스트 페이징", notes = "최근 등록된 상품순으로 전체 상품 반환")
    @GetMapping("/list/page")
    public ResponseEntity<> listItemPage() {

        return new ResponseEntity<ResponseDto>(
                new ResponseDto(200, "success", itemService.listItem())
        , HttpStatus.OK);
    }


}
