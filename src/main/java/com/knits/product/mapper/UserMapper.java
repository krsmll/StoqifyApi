package com.knits.product.mapper;

import com.knits.product.dto.UserDto;
import com.knits.product.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    List<UserDto> toUserDtoList(List<User> users);

    List<User> toUserList(List<UserDto> userDtos);

    void update(@MappingTarget User group, UserDto updatedGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User group, UserDto updatedGroup);
}
