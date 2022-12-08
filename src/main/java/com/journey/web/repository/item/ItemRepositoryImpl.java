package com.journey.web.repository.item;

import com.journey.web.dto.item.ItemListDto;
import com.journey.web.dto.item.ItemListRequestCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemListDto> findItemListByFilter(ItemListRequestCondition condition, Pageable pageable) {

        return new PageImpl<>(dtolist, pageable, total);
    }
}
