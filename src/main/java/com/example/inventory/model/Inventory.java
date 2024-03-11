package com.example.inventory.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Inventory {

    /*
     * Overriding the equals and hashcode methods of object
     * to have only unique elements of inventory object
     * using itemId and itemName
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return itemId.equals(inventory.itemId) && itemName.equals(inventory.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName);
    }

    private String itemId;
    private String itemName;
    private String itemPrice;
    private String quantity;

    public Inventory(String itemId, String itemName, String itemPrice, String quantity){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }
}
