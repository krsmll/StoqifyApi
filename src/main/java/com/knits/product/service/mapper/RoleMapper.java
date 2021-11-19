package com.knits.product.service.mapper;

import com.knits.product.dto.RoleDTO;
import com.knits.product.entity.Role;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper {

    public Role toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }
        Role entity = new Role();
        entity.setRoleId(dto.getRoleId());
        entity.setRoleId(dto.getRoleId());
        return entity;
    }

    public RoleDTO toDto(Role entity) {
        if(entity == null){
            return null;
        }
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        return dto;

    }

    public void partialUpdate(Role entity, RoleDTO dto) {
        if (entity == null) {
            return;
        }
        if (dto.getRoleId() != null) {
            entity.setRoleId(dto.getRoleId());
        }
        if (dto.getRoleName() != null) {
            entity.setRoleName(dto.getRoleName());
        }
    }

    public void update(Role entity, RoleDTO dto) {
        if (entity == null) {
            return;
        }
        entity.setRoleId(dto.getRoleId());
        entity.setRoleName(dto.getRoleName());
    }

    public List<RoleDTO> toDtO(List<Role> entityList) {
        if (entityList== null) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<>(entityList.size());
        for (Role entity : entityList) {
            list.add(toDto(entity));
        }
        return list;
    }

    public List<Role> toEntity(List<RoleDTO> dtoList) {
        if (dtoList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<>(dtoList.size());
        for (RoleDTO roleDTO : dtoList) {
            list.add(toEntity(roleDTO));
        }
        return list;
    }


}
