package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.UpdateItemRequest;
import com.pgl1.database.dto.request.CreateItemRequest;
import com.pgl1.database.dto.response.ViewItemResponse;
import com.pgl1.database.model.entity.Item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    Item itemWriteDTOToItem(CreateItemRequest createItemRequest);


    @Mapping(target = "createdTimestamp", ignore = true)
    Item itemUpdateDTOToItem(UpdateItemRequest updateItemRequest);

    ViewItemResponse itemToItemViewDTO(Item item);
}
