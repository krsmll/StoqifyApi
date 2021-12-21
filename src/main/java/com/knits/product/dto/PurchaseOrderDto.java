package com.knits.product.dto;

import lombok.Data;
import java.time.LocalDate;

/**
 * This is Data transfer object class to handle request purchase data
 * @author Soumen Banerjee
 */
@Data
public class PurchaseOrderDto {

    private Long id;
    private Long supplierCompanyId;
    private Long customerComapnyId;
    private Boolean status;
    private String type;
    private LocalDate orderDate;
    private LocalDate shippingDate;
    private LocalDate deliverDate;
    private LocalDate cancelDate;
    private OrderLineDto orderLine;
}
