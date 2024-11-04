package com.kingjinho.ecommerce.repository;

import com.kingjinho.ecommerce.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        if (item.getId() == null) { //없으면 신규
            em.persist(item);
        } else {
            //준영속 상태의 엔티티를 영속 상태로 변경(ItemService.class updateItem(Long itemId, Book param)와 같음)
            //그렇다고 해서 item은 영속성 컨텍스트로 안바뀐다
            //merge에 의해 반한된 값 T가 영속성 컨텍스트다
            em.merge(item); //있으면 수정
        }
        return item.getId();
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}
