package com.journey.web;

import com.journey.web.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
        initService.dbInit4();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Item item = createItem("참이슬", 10000, "맛있어요", 21.0, 700, "진로", "쌀", "S", 100, TRUE,"234525");
            em.persist(item);
        }

        public void dbInit2() {
            Item item = createItem("테라", 12000, "맛있어요", 7.0, 700, "진로", "쌀", "S", 300, FALSE,"123455");
            em.persist(item);
        }

        public void dbInit3() {
            Item item = createItem("복분자주", 20000, "맛있어요", 17.0, 500, "진로", "복분자", "W", 50, FALSE, "123314");
            em.persist(item);
        }

        public void dbInit4() {
            Item item = createItem("막걸리", 8000, "맛있어요", 21.0, 750, "진로", "쌀, 누룩", "S", 200, TRUE,"1234");
            em.persist(item);
        }

        private Item createItem(String name, int price, String info, double degree, int size, String company, String material, String type, int stockQuantity, boolean isSoldout,String image_url) {

            Item item = new Item();
            item.setName(name);
            item.setPrice(price);
            item.setInfo(info);
            item.setDegree(degree);
            item.setSize(size);
            item.setCompany(company);
            item.setMaterial(material);
            item.setType(type);
            item.setStockQuantity(stockQuantity);
            item.setSoldout(isSoldout);
            item.setImage_url(image_url);
            return item;
        }
    }
}
