package com.journey.web.service;

import com.journey.web.domain.item.Item;
import com.journey.web.dto.item.*;
import com.journey.web.exception.CustomException;
import com.journey.web.repository.item.ItemRepository;
import com.journey.web.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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

    /**
     * 상품 등록, 수정, 삭제
     */
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
    /**
    * 전체 상품
    */
    // 전체 상품 목록 조회
    public List<ItemDto> listItem() {
        return itemRepository.getItem();
    }

    // 전체 상품 목록 페이징 하여 조회
    public Page<ItemListDto> getItemByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());

        List<ItemListDto> itemListDtoPage = itemRepository.findItemListDtoPage(pageable);
        int total = itemRepository.findTotal();
        return new PageImpl<>(itemListDtoPage, pageable, total);
    }

    /**
     * 특정 상품
     */
    // 특정 상품 id로 조회
    public ItemResponseDto findById(Long itemId) {
        Item entity = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + itemId));

        return new ItemResponseDto(entity);
    }

    // 특정 상품 키워드로 조회
    public List<ItemListDto> searchItemList(String keyword) {

        return itemRepository.searchItemByName(keyword);
    }

    /**
     * 상품 다중 조건 검색
     */
    public Page<ItemListDto> findItemListByFilter(ItemListRequestCondition condition, Pageable pageable) {
        if (condition.getType() != null) {
            return itemRepository.findItemListByFilter(condition, pageable);
        } else {
            return itemRepository.findItemListTypeNull(condition.getType(), pageable);
        }
    }

}
