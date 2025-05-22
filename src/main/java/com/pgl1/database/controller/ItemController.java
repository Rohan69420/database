package com.pgl1.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import com.pgl1.database.service.ItemService;
import org.springframework.http.HttpStatus;

import com.pgl1.database.dto.request.ItemCreateDTO;
import com.pgl1.database.dto.request.ItemUpdateDTO;
import com.pgl1.database.dto.response.ItemViewDTO;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<ItemViewDTO> createItem(ItemCreateDTO itemCreateDTO){
        ItemViewDTO savedItem = itemService.createItem(itemCreateDTO);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ItemViewDTO> updateItem(ItemUpdateDTO itemUpdateDTO){
        ItemViewDTO updatedItem = itemService.updateItem(itemUpdateDTO);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
