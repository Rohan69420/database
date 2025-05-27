package com.pgl1.database.controller;

import com.pgl1.database.dto.response.ViewItemResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import com.pgl1.database.service.ItemService;
import org.springframework.http.HttpStatus;

import com.pgl1.database.dto.request.CreateItemRequest;
import com.pgl1.database.dto.request.UpdateItemRequest;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<GenericAPIResponse<ViewItemResponse>> createItem(@Valid @RequestBody CreateItemRequest createItemRequest, HttpServletRequest request){
        ViewItemResponse savedItem = itemService.createItem(createItemRequest);
        return new ResponseEntity<>(ResponseUtil.success(savedItem, "An item has been created", request.getRequestURI()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericAPIResponse<ViewItemResponse>> updateItem(@Valid @RequestBody UpdateItemRequest updateItemRequest, @PathVariable Long id, HttpServletRequest request){
        ViewItemResponse updatedItem = itemService.updateItem(updateItemRequest);
        return new ResponseEntity<>(ResponseUtil.success(updatedItem, "Item" + id.toString() + "has been updated", request.getRequestURI()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericAPIResponse<Void>> deleteItem(@PathVariable Long id, HttpServletRequest request){
        itemService.deleteItem(id);
        return new ResponseEntity<>(ResponseUtil.success(null, "Item with id: " + id.toString() + "has been deleted.", request.getRequestURI()) ,HttpStatus.NO_CONTENT);
    }
}
