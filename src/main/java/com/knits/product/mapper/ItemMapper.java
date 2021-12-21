package com.knits.product.mapper;

import com.knits.product.dto.ItemDto;
import com.knits.product.entity.Item;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    List<Item> toEntityList(List<ItemDto> itemDtos);

    List<ItemDto> toDtoList(List<Item> item);

    ItemDto toDto(Item item);

    Item toEntity(ItemDto itemDto);

    void update(@MappingTarget Item item, ItemDto updatedItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Item item, ItemDto updatedItem);
}
