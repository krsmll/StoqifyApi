package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.knits.product.dto.groups.DeleteGroup;
import com.knits.product.dto.groups.UpdateGroup;

@Data
public class UserDto {

    @NotNull(groups = DeleteGroup.class, message = "ID can not be null")
    private Long id;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User login should contain only characters")
    @NotNull(message = "Login can not be null")
    private String login;
    private String password;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User first name should contain only characters")
    @NotNull(message = "First name can not be null")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User last name should contain only characters")
    @NotNull(message = "Last name can not be null")
    private String lastName;

    @Pattern(regexp = "^[A-Za-z0-9@]*$", message = "Driver last name should contain only characters")
    @NotNull(message = "Email can not be null")
    private String email;
    private Boolean active;

    @NotNull(groups = UpdateGroup.class, message = "Role id can not be null")
    private Long roleId;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User role name should contain only characters")
    private String roleName;

    @NotNull(message = "Group ID can not be null")
    private Long groupId;

    @Pattern(regexp = "^[A-Za-z]*$", message = "User group name should contain only characters")
    private String groupName;
}
