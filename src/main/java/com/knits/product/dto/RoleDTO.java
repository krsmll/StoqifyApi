package com.knits.product.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * This is an entity which is responsible to save and fetch role table data
 */

@Data
public class RoleDTO {
    private Long roleId;
    private String roleName;
}
