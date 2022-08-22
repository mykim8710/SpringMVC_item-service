package com.example.itemservice.domain.item;

import lombok.*;
@Getter
@Setter
@ToString
public class Item {
    private Long itemId;
    private String itemName;
    private Integer itemPrice;
    private Integer itemQuantity;

    public Item() {
    }

    public Item(String itemName, Integer itemPrice, Integer itemQuantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }
}
