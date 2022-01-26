package com.knits.product.mapper;

import org.mapstruct.Mapper;
import com.knits.product.dto.ItemLineDto;
import com.knits.product.entity.ItemLine;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemLineMapper {

    @Mapping(source = "id", target = "itemLineid")
    ItemLineDto toDto(ItemLine itemLine);
    ItemLine toEntity(ItemLineDto itemDto);

    List<ItemLineDto> toDtoList(List<ItemLine> itemLines);
}
