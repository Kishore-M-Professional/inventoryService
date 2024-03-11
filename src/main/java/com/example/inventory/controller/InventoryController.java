package com.example.inventory.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
/*
* Below RequestMapping can be commented because
* in application.properties added a property
* server.servlet.context-path to
* have common prefix in endpoint
* @RequestMapping("/inventoryservice/v1")
*
*/
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/all")
    public ResponseEntity<Set<Inventory>> getAll(){
        return ResponseEntity.ok(inventoryService.getAllItems());
    }

    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory newItem){
        return ResponseEntity.ok(inventoryService.addItem(newItem));
    }

    /*
     * passing the Id in the endpoint
     * as pathVariable
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable(name="id") String id, @RequestBody Inventory updateItem){
        return ResponseEntity.ok(inventoryService.updateItem(id,updateItem));
    }

    /*
     * passing the Id as a parameter
     * as request param
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteInventory(@RequestParam(name="id") String id){
        return ResponseEntity.ok(inventoryService.deleteItem(id));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllInventory(){
        return ResponseEntity.ok(inventoryService.deleteAllItems());
    }
}
