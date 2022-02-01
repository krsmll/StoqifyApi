package com.knits.product.mapper;

import com.knits.product.dto.AdvancedShippingNoticeDto;
import com.knits.product.entity.AdvancedShippingNotice;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdvancedShippingNoticeMapper {
    AdvancedShippingNotice toEntity(AdvancedShippingNoticeDto dto);

    AdvancedShippingNoticeDto toDto(AdvancedShippingNotice entity);

    void update(@MappingTarget AdvancedShippingNotice advancedShippingNotice, AdvancedShippingNoticeDto updatedAdvancedShippingNotice);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget AdvancedShippingNotice advancedShippingNotice, AdvancedShippingNoticeDto updatedAdvancedShippingNotice);
}
