package com.knits.product.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
public class ItemDto {

    @NotBlank(message = "The id is required.")
    private long id;

    @NotBlank(message = "The name is required.")
    private String name;

    @NotBlank(message = "The description is required")
    private String description;

    @Null
    private Date createdAt;
}
