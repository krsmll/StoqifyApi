package com.knits.product.mapper;

import com.knits.product.dto.UserDto;
import com.knits.product.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper1 {

    List<UserDto> toUserList(List<User> values);
}
