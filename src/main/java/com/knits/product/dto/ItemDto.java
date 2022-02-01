package com.knits.product.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class ItemDto {

    @NotNull(message = "Item ID can not be null")
    private Long id;

    @NotBlank(message = "The name is required.")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Item name should contain only characters")
    private String name;

    @NotBlank(message = "The description is required")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Item description should contain only characters")
    private String description;

    @NotBlank(message = "The quantity is required")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Driver quantity should be only numbers")
    private Integer quantity;

    @Null
    private LocalDate enteredAt;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Item status should contain only characters")
    private String status;
}
