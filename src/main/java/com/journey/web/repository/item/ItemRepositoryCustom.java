package com.journey.web.repository.item;

import com.journey.web.dto.item.ItemListDto;
import com.journey.web.dto.item.ItemListRequestCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    Page<ItemListDto> findItemListByFilter(ItemListRequestCondition condition, Pageable pageable);

    Page<ItemListDto> findItemListTypeNull(List<String> type, Pageable pageable);

}
