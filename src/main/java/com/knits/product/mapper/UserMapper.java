package com.knits.product.mapper;

import com.knits.product.dto.UserDto;
import com.knits.product.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    @Mapping(target = "roleName", source = "role.roleName")
    @Mapping(target = "groupName", source = "group.name")
    UserDto toDto(User user);

    UserDto toUserDtoList(User users);

    List<User> toUserList(List<UserDto> userDtos);

    void update(@MappingTarget User group, UserDto updatedGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User group, UserDto updatedGroup);
}
