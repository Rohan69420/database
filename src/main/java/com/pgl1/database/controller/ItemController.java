package com.pgl1.database.controller;

import com.pgl1.database.model.entity.Item;
import com.pgl1.database.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(Item item){
        Item savedItem = itemService.createItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Item> updateItem(Item item){
        Item updatedItem = itemService.updateItem(item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
