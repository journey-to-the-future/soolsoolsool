package com.journey.web.service;

import com.journey.web.domain.item.Item;
import com.journey.web.dto.item.ItemDto;
import com.journey.web.dto.item.ItemListDto;
import com.journey.web.dto.item.ItemResponseDto;
import com.journey.web.dto.item.ItemUpdateDto;
import com.journey.web.exception.CustomException;
import com.journey.web.repository.ItemRepository;
import com.journey.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.ItemList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.journey.web.exception.ErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

//    @Transactional
    public void registerItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(ItemUpdateDto itemUpdateDto, Long memberId) {
        Item item = itemRepository.findById(itemUpdateDto.getItemId())
                .orElseThrow(() -> new CustomException(ITEM_NOT_FOUND));

        //받아오는 멤버 아이디와 상품 등록 아이디가 같은 경우 수정
        if(memberId == item.getCreatorId()){
            item.setName(itemUpdateDto.getUpdateName());
            item.setPrice(itemUpdateDto.getUpdatePrice());
            item.setInfo(itemUpdateDto.getUpdateInfo());
            item.setMaterial(itemUpdateDto.getUpdateMaterial());
        }else {
            throw new CustomException(CANNOT_MODIFY_ITEM);
        }
    }

    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    // ------------------------ 상품 조회 관련 ------------------------

    // 전체 상품 목록 조회
    public List<ItemDto> listItem() {
        return itemRepository.getItem();
    }

    // 전체 상품 목록 페이징 하여 조회
    public List<ItemListDto> getItemByPage(Long id) {
        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());
        return itemRepository.findItemListDtoPage(id, pageable);
    }

    // 특정 상품 조회
    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public ItemResponseDto findById(Long itemId) {
        Item entity = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + itemId));

        return new ItemResponseDto(entity);
    }



}
