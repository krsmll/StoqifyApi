package com.knits.product.mapper;

import com.knits.product.dto.UserDto;
import com.knits.product.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(UserDto userDto);

    @Mapping(source = "user.group.id", target = "groupId")
    @Mapping(source = "user.role.id", target = "roleId")
    @Mapping(source = "user.group.name", target = "groupName")
    @Mapping(source = "user.role.roleName", target = "roleName")
    UserDto toDto(User user);

    void update(@MappingTarget User user, UserDto updatedUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User user, UserDto updatedUser);
}
