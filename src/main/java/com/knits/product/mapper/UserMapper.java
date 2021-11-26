package com.knits.product.mapper;

import com.knits.product.dto.UserDto;
import com.knits.product.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(UserDto userDto);

    @Mapping(target = "groupName", source = "group.name")
    @Mapping(target = "roleName", source = "role.roleName")
    UserDto toDto(User user);

    void update(@MappingTarget User group, UserDto updatedGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User group, UserDto updatedGroup);
}
