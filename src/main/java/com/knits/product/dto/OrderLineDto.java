package com.knits.product.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a Data Transfer Object class to handle requested data
 * @author Soumen Banerjee
 */
@Data
public class OrderLineDto {

    private Long id;
    private Integer itemId;
    private Float unitPrice;
    private Integer orderedQuantity;
    private Integer shippedQuantity;
    private Integer deliveredQuantity;
}
