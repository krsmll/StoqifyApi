package com.knits.product.mapper;

import com.knits.product.dto.RoleDTO;
import com.knits.product.entity.Role;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper1 {

    List<RoleDTO> toRoleDtoList(List<Role> roles);
    List<Role> toEntityList(List<RoleDTO> roles);

    Role toRoleEntity(RoleDTO roleDTO);
    RoleDTO toRoleEntity(Role roleEntity);
}
