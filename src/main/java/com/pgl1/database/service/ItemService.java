package com.pgl1.database.service;

import com.pgl1.database.dto.request.UpdateItemRequest;
import com.pgl1.database.dto.request.CreateItemRequest;
import com.pgl1.database.repository.ItemRepository;
import com.pgl1.database.dto.response.ViewItemResponse;
import com.pgl1.database.mapper.ItemMapper;
import com.pgl1.database.model.entity.Item;

import org.springframework.stereotype.Service;

@Service
public class ItemService {
    public final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper){
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ViewItemResponse createItem(CreateItemRequest createItemRequest){
        Item createdItem =  itemRepository.save(itemMapper.itemWriteDTOToItem(createItemRequest));
        return itemMapper.itemToItemViewDTO(createdItem);
    }

    public ViewItemResponse updateItem(UpdateItemRequest updateItemRequest){
        Item updatedItem =  itemRepository.save(itemMapper.itemUpdateDTOToItem(updateItemRequest));
        return itemMapper.itemToItemViewDTO(updatedItem);
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
}
