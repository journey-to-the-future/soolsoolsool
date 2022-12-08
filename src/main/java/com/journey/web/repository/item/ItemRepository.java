package com.journey.web.repository.item;

import com.journey.web.domain.item.Item;
import com.journey.web.dto.item.ItemDto;
import com.journey.web.dto.item.ItemListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select new com.journey.web.dto.item.ItemDto(i.id, i.name, i.price, i.info, i.degree, i.size, i.company, i.material, i.soolType, i.stockQuantity, i.imageUrl)" +
            "from Item i")
    List<ItemDto> getItem();

    @Query("select new com.journey.web.dto.item.ItemListDto(i.id, i.name, i.price, i.degree, i.size, i.company, i.material, i.soolType, i.stockQuantity, i.imageUrl)" +
            "from Item i")
    List<ItemListDto> findItemListDtoPage(Pageable pageable);

    @Query("select count(i.id) from Item i")
    int findTotal();

    @Query("select new com.journey.web.dto.item.ItemListDto(i.id, i.name, i.price, i.degree, i.size, i.company, i.material, i.soolType, i.stockQuantity, i.imageUrl)" +
            "from Item i where i.name like %:keyword%")
    List<ItemListDto> searchItemByName(@Param("keyword") String keyword);
}
