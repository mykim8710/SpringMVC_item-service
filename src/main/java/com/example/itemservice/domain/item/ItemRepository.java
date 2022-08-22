package com.example.itemservice.domain.item;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    public Item save(Item item) {
        item.setItemId(++sequence);
        store.put(item.getItemId(), item);
        return item;
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    public void update(Long itemId, Item updateItem) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateItem.getItemName());
        findItem.setItemPrice(updateItem.getItemPrice());
        findItem.setItemQuantity(updateItem.getItemQuantity());
    }

    public void delete(Long itemId) {
        store.remove(itemId);
    }

    public void clearStore() {
        store.clear();
    }
}
