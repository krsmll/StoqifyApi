package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * This is an entity class to join Order line with purchase order
 * @author Soumen Banerjee
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase_order_line")
public class PurchaseOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_line_id")
    private Long orderLineId;

    @Column(name = "purchase_order_id")
    private Long purchaseOrderId;
}
