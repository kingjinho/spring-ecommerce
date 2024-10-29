package com.kingjinho.ecommerce.service;

import com.kingjinho.ecommerce.domain.item.Album;
import com.kingjinho.ecommerce.domain.item.Item;
import com.kingjinho.ecommerce.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    @Test
    public void 아이템_등록() throws Exception {
        //given
        Item item = new Album();

        //when
        Long savedItemId = itemService.saveItem(item);

        //then
        assertEquals(item, itemRepository.findOne(savedItemId));
    }

}