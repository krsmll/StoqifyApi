package com.knits.product.mapper;

import org.mapstruct.Mapper;
import com.knits.product.dto.DockDto;
import com.knits.product.entity.Dock;

@Mapper(componentModel = "spring")
public interface DockMapper {

    DockDto convertToDockDto(Dock dock);
}
