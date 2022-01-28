package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    private Long id;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User login should contain only characters")
    private String login;
    private String password;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User first name should contain only characters")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User last name should contain only characters")
    private String lastName;

    @Pattern(regexp = "^[A-Za-z0-9@]*$", message = "Driver last name should contain only characters")
    private String email;
    private Boolean active;
    private Long roleId;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User role name should contain only characters")
    private String roleName;
    private Long groupId;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User group name should contain only characters")
    private String groupName;
}
