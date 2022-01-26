package com.knits.product.mapper;

import org.mapstruct.Mapper;
import com.knits.product.dto.ItemLineDto;
import com.knits.product.entity.ItemLine;

@Mapper(componentModel = "spring")
public interface ItemLineMapper {

    ItemLineDto toDto(ItemLine itemLine);
    ItemLine toEntity(ItemLineDto itemDto);
}
