package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.Pattern;


/**
 * This is an entity which is responsible to save and fetch role table data
 */

@Data
public class RoleDto {
    private Long id;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Role name should contain only characters")
    private String roleName;
}
