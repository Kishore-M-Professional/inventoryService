package com.example.inventory.service;

import com.example.inventory.model.Inventory;

import java.util.Set;

public interface InventoryService {

    Set<Inventory> getAllItems();

    Inventory addItem(Inventory newItem);

    Inventory updateItem(String Id, Inventory updateItem);

    String deleteItem(String Id);

    String deleteAllItems();

}
