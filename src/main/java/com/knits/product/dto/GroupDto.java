package com.knits.product.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
public class GroupDto {
    @NotNull(groups = UpdateGroup.class)
    private long id;

    @NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
    @Max(128)
    private String name;

    @Null
    private Boolean isActive;

    @Null
    private Date createdAt = new Date();

    public interface InsertGroup {
    }

    public interface UpdateGroup {
    }
}

