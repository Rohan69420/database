package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.ItemUpdateDTO;
import com.pgl1.database.dto.request.ItemCreateDTO;
import com.pgl1.database.dto.response.ItemViewDTO;
import com.pgl1.database.model.entity.Item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    Item itemWriteDTOToItem(ItemCreateDTO itemCreateDTO);


    @Mapping(target = "createdTimestamp", ignore = true)
    Item itemUpdateDTOToItem(ItemUpdateDTO itemUpdateDTO);

    ItemViewDTO itemToItemViewDTO(Item item);
}
