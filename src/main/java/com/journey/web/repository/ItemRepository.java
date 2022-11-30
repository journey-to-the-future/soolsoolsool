package com.journey.web.repository;

import com.journey.web.domain.item.Item;
import com.journey.web.dto.item.ItemDto;
import com.journey.web.dto.item.ItemListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i ORDER BY i.id DESC")
    List<ItemDto> getItem();

    List<ItemListDto> findItemListDtoPage(Long id, Pageable pageable);

//    private final EntityManager em;
//
//    public void save(Item item) {
//        if (item.getId() == null) {
//            em.persist(item);
//        } else {
//            em.merge(item);
//        }
//    }
//
//    public Item findOne(Long id) {
//        return em.find(Item.class, id);
//    }
//
//    public List<Item> findAll() {
//        return em.createQuery("select i from Item i", Item.class)
//                .getResultList();
//    }
}
