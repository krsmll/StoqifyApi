package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * This is order line entity class
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "orderline")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

    @Column(name = "ordered_qnt", nullable = false)
    private Integer orderedQuantity;

    @Column(name = "delivered_qnt", nullable = false)
    private Integer deliveredQuantity;

    @Column(name = "shipped_qnt", nullable = false)
    private Integer shippedQuantity;
}
