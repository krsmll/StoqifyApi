package com.knits.product.mapper;

import com.knits.product.dto.RoleDto;
import com.knits.product.entity.Role;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    List<RoleDto> toRoleDtoList(List<Role> roles);

    List<Role> toEntityList(List<RoleDto> roles);

    Role toEntity(RoleDto roleDTO);

    RoleDto toDto(Role roleEntity);

    void update(@MappingTarget Role group, RoleDto updatedGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Role group, RoleDto updatedGroup);
}
