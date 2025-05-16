package com.pgl1.database.service;

import com.pgl1.database.model.entity.Item;
import com.pgl1.database.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    public final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    public Item updateItem(Item item){
        return itemRepository.save(item);
    }

    public void deleteItem(Integer itemId){
        itemRepository.deleteById(itemId);
    }
}
