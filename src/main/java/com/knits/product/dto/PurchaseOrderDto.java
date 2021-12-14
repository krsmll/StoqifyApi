package com.knits.product.dto;

import lombok.Data;

@Data
public class PurchaseOrderDto {

    private Long id;
    private Long itemId;
    private float unitPrice;
    private int orderedQuantity;
    private int deliveredQuantity;
    private int shippedQuantity;
}
