package com.knits.product.dto;

import com.knits.product.dto.groups.InsertGroup;
import com.knits.product.dto.groups.UpdateGroup;
import lombok.Data;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
public class GroupDto {
    @NotNull(groups = UpdateGroup.class)
    private long id;

    @NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
    @Size(groups = {InsertGroup.class, UpdateGroup.class}, max = 128)
    @Pattern(groups = {InsertGroup.class, UpdateGroup.class},
            regexp = "^[A-Za-z]*$", message = "Group name should contain only characters")
    private String name;

    @Null
    private Boolean isActive;

    @Null
    private Date createdAt;

    @NotNull
    private List<UserDto> users;
}

