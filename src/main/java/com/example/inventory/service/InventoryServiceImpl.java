package com.example.inventory.service;

import com.example.inventory.model.Inventory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class InventoryServiceImpl implements InventoryService{
    private static Set<Inventory> inventorySet = new HashSet<>();

    /*
     * created static block below
     * to have 3 elements in Inventory
     * before load the class
     * Static block will load first
     * before executing class statements
     */
    static {
        Inventory item1 = new Inventory("iphone12","Iphone 12","40000","40");
        Inventory item2 = new Inventory("iphone13","Iphone 13","60000","68");
        Inventory item3 = new Inventory("iphone14","Iphone 14","75000","80");
        inventorySet.addAll(Arrays.asList(item1,item2,item3));
    }

    @Override
    public Set<Inventory> getAllItems() {
        return inventorySet;
    }

    @Override
    public Inventory addItem(Inventory newItem) {
        if(inventorySet.add(newItem)){
            return newItem;
        }else{
            return new Inventory("","","","");
        }
    }

    @Override
    public Inventory updateItem(String Id, Inventory updateItem) {
        return inventorySet.stream()
                .filter(x -> x.getItemId().equals(Id))
                .map(x -> updateInventoryItem(x,updateItem))
                .findFirst()
                .get();
    }

    @Override
    public String deleteItem(String Id) {
        Inventory itemToDelete = inventorySet.stream()
                .filter(x -> x.getItemId().equals(Id))
                .findFirst()
                .get();
        if(inventorySet.remove(itemToDelete)){
            return Id+" has been deleted successfully!!!";
        }
        return Id+" cannot be deleted!!!";
    }

    @Override
    public String deleteAllItems() {
        if(inventorySet.removeAll(inventorySet)){
            return "All items in the inventory has been deleted successfully!!!";
        }
        return "Items in the inventory cannot be deleted!!!";
    }

    private Inventory updateInventoryItem(Inventory item, Inventory updateItem) {
        item.setItemName(updateItem.getItemName());
        item.setItemPrice(updateItem.getItemPrice());
        item.setQuantity(updateItem.getQuantity());
        return item;
    }
}
