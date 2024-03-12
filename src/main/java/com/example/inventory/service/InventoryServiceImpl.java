package com.example.inventory.service;

import com.example.inventory.model.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
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
        log.info("3 Items added in the Inventory");
        inventorySet.addAll(Arrays.asList(item1,item2,item3));
    }

    @Override
    public Set<Inventory> getAllItems() {
        log.info("Returning inventory items");
        return inventorySet;
    }

    @Override
    public Inventory addItem(Inventory newItem) {
        if(inventorySet.add(newItem)){
            log.info("New item of ID {} added to the inventory!!!",newItem.getItemId());
            return newItem;
        }else{
            log.info("New item of ID {} is NOT added to the inventory!!!",newItem.getItemId());
            return new Inventory("","","","");
        }
    }

    @Override
    public Inventory updateItem(String Id, Inventory updateItem) {
        log.info("Updating the item of ID {}",Id);
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
            log.info("ID '{}' of item deleted",Id);
            return Id+" has been deleted successfully!!!";
        }
        log.info("ID '{}' of item NOT deleted",Id);
        return Id+" cannot be deleted!!!";
    }

    @Override
    public String deleteAllItems() {
        if(inventorySet.removeAll(inventorySet)){
            log.info("Inventory items deleted");
            return "All items in the inventory has been deleted successfully!!!";
        }
        log.info("Inventory items NOT deleted");
        return "Items in the inventory cannot be deleted!!!";
    }

    private Inventory updateInventoryItem(Inventory item, Inventory updateItem) {
        item.setItemName(updateItem.getItemName());
        item.setItemPrice(updateItem.getItemPrice());
        item.setQuantity(updateItem.getQuantity());
        log.info("Item has been updated - {}",item);
        return item;
    }
}
