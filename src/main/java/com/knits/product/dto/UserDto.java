package com.knits.product.dto;

import lombok.Data;
import org.mapstruct.Mapping;

@Data
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
    private Long roleId;
    private String roleName;
    private Long groupId;
    private String groupName;
}
