package com.example.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();   // 스프링 없이 테스트

    @AfterEach  // 각 테스트 마칠때 마다 실행
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("상품저장 save 함수 테스트")
    void saveTest() {
        // given
        Item itemA = new Item("itemA", 1000, 3);

        // when
        Item saveItem = itemRepository.save(itemA);

        // then
        Item findItem = itemRepository.findById(saveItem.getItemId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    @DisplayName("상품목록 findAll 함수 테스트")
    void findAllTest() {
        // given
        Item itemA = new Item("itemA", 1000, 3);
        Item itemB = new Item("itemB", 2000, 4);
        Item itemC = new Item("itemC", 3000, 5);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items.size()).isEqualTo(3);
        assertThat(items).contains(itemA, itemB, itemC);
    }

    @Test
    @DisplayName("상품수정 update 함수 테스트")
    void updateTest() {
        // given
        Item itemA = new Item("itemA", 1000, 3);
        Item saveItem = itemRepository.save(itemA);
        
        // when
        Item updateItem = new Item("itemA_edit", 3000, 5);
        itemRepository.update(saveItem.getItemId(), updateItem);

        // then
        Item findItem = itemRepository.findById(saveItem.getItemId());
        assertThat(updateItem.getItemName()).isEqualTo(findItem.getItemName());
        assertThat(updateItem.getItemPrice()).isEqualTo(findItem.getItemPrice());
        assertThat(updateItem.getItemQuantity()).isEqualTo(findItem.getItemQuantity());
    }


    @Test
    @DisplayName("상품삭제 delete 함수 테스트")
    void deleteTest() {
        // given
        Item itemA = new Item("itemA", 1000, 3);
        Item itemB = new Item("itemB", 2000, 4);
        Item itemC = new Item("itemC", 3000, 5);

        Item saveItemA = itemRepository.save(itemA);
        Item saveItemB = itemRepository.save(itemB);
        Item saveItemC = itemRepository.save(itemC);

        // when
        itemRepository.delete(saveItemA.getItemId());
        itemRepository.delete(saveItemB.getItemId());

        // then
        List<Item> items = itemRepository.findAll();
        assertThat(items.size()).isEqualTo(1);
        assertThat(itemRepository.findById(saveItemA.getItemId())).isEqualTo(null);
        assertThat(itemRepository.findById(saveItemB.getItemId())).isEqualTo(null);
        assertThat(itemRepository.findById(saveItemC.getItemId())).isEqualTo(saveItemC);
    }

}