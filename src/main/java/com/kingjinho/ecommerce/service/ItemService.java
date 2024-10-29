package com.kingjinho.ecommerce.service;

import com.kingjinho.ecommerce.domain.item.Item;
import com.kingjinho.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional//가까이에 있는게 더 우선권을 가짐
    public Long saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }
}
