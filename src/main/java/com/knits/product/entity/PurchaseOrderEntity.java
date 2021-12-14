package com.knits.product.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase_order")
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

  /*  @Column(name = "item_id")
    private int itemId;*/

    @Column(name = "unit_price")
    private float unitPrice;

    /*@Column(name = "ordered_qnt")
    private int orderedQuantity;

    @Column(name = "delivered_qnt")
    private int deliveredQuantity;

    @Column(name = "shipped_qnt")
    private int shippedQuantity;*/
}
