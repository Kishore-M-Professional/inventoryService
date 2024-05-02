package com.example.inventory.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*
     * added logger of slf4j logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Inventory>> getAll(){
        LOGGER.info("invoking '/all' endpoint");
        return ResponseEntity.ok(inventoryService.getAllItems());
    }

    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory newItem){
        LOGGER.info("invoking '/add' endpoint");
        Inventory resp = inventoryService.addItem(newItem);
        if(resp.getItemId().isBlank()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
        return ResponseEntity.ok(resp);
    }

    /*
     * passing the Id in the endpoint
     * as pathVariable
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable(name="id") String id, @RequestBody Inventory updateItem){
        LOGGER.info("invoking '/update' endpoint for ID:{}",id);
        return ResponseEntity.ok(inventoryService.updateItem(id,updateItem));
    }

    /*
     * passing the Id as a parameter
     * as request param
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteInventory(@RequestParam(name="id") String id){
        LOGGER.info("invoking '/delete' endpoint for ID:{}",id);
        return ResponseEntity.ok(inventoryService.deleteItem(id));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllInventory(){
        LOGGER.info("invoking '/delete/all' endpoint");
        return ResponseEntity.ok(inventoryService.deleteAllItems());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Inventory> getItem(@PathVariable String id){
        LOGGER.info("invoking '/get/{}' endpoint",id);
        return ResponseEntity.ok(inventoryService.getItem(id));
    }

}
