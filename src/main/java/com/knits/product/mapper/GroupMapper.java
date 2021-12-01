package com.knits.product.mapper;

import com.knits.product.dto.GroupDto;
import com.knits.product.entity.Group;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    List<Group> toEntityList(List<GroupDto> groupDtos);

    List<GroupDto> toDtoList(List<Group> groups);

    GroupDto toDto(Group group);

    Group toEntity(GroupDto groupDto);

    void update(@MappingTarget Group group, GroupDto updatedGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Group group, GroupDto updatedGroup);
}
