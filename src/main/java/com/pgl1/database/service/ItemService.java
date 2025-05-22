package com.pgl1.database.service;

import com.pgl1.database.dto.request.ItemUpdateDTO;
import com.pgl1.database.dto.request.ItemCreateDTO;
import com.pgl1.database.repository.ItemRepository;
import com.pgl1.database.dto.response.ItemViewDTO;
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

    public ItemViewDTO createItem(ItemCreateDTO itemCreateDTO){
        Item createdItem =  itemRepository.save(itemMapper.itemWriteDTOToItem(itemCreateDTO));
        return itemMapper.itemToItemViewDTO(createdItem);
    }

    public ItemViewDTO updateItem(ItemUpdateDTO itemUpdateDTO){
        Item updatedItem =  itemRepository.save(itemMapper.itemUpdateDTOToItem(itemUpdateDTO));
        return itemMapper.itemToItemViewDTO(updatedItem);
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
}
