package com.knits.product.mapper;

import com.knits.product.dto.ItemDto;
import com.knits.product.entity.Item;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    List<Item> toEntityList(List<ItemDto> itemDtos);
    List<ItemDto> toDtoList(List<Item> item);

    List<ItemDto> mapDifferentFields(List<ItemDto> itemDtos);

    ItemDto toDto(Item item);
    Item toEntity(ItemDto itemDto);

    void update(@MappingTarget Item item, ItemDto updatedItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Item item, ItemDto updatedItem);
}
