package com.knits.product.mapper;

import com.knits.product.dto.GroupDto;
import com.knits.product.entity.Group;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDto toDto(Group group);

    Group toEntity(GroupDto groupDto);

    void update(@MappingTarget Group group, GroupDto updatedGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Group group, GroupDto updatedGroup);
}
