package com.knits.product.mapper;

import com.knits.product.dto.RoleDto;
import com.knits.product.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    List<RoleDto> toRoleDtoList(List<Role> roles);
    List<Role> toEntityList(List<RoleDto> roles);

    Role toRoleEntity(RoleDto roleDTO);
    RoleDto toRoleEntity(Role      roleEntity);

    void partialUpdate(@MappingTarget Role roleEntity, RoleDto roleDto);

    void update(@MappingTarget Role roleEntity, RoleDto roleDto);
}
