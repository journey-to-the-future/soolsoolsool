package com.journey.web.service;

import com.journey.web.domain.item.Item;
import com.journey.web.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

//    @Transactional
    public void register(Item item) {
        itemRepository.save(item);
    }

    public void update(Item item) {
        itemRepository.save(item);
    }

    public void delete(Long itemId) {
        itemRepository.deleteById(itemId);
    }

//    @Transactional
//    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
//        Item findItem = itemRepository.findOne(itemId);
//        findItem.setName(name);
//        findItem.setPrice(price);
//        findItem.setStockQuantity(stockQuantity);
//    }

    // 전체 상품 목록 조회
    public List<Item> findItems() {
        return itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    // 특정 상품 조회
    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public Page<Item> list(int page) {
        return itemRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id")));
    }
}
