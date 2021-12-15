package com.knits.product.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * This is a Data Transfer Object class to handle requested data
 * @author Soumen Banerjee
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDto {

    private Long id;
    private Long itemId;
    private float unitPrice;
    private int orderedQuantity;
    private int deliveredQuantity;
    private int shippedQuantity;
}
