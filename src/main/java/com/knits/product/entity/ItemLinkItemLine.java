package com.knits.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_link_item_line")
public class ItemLinkItemLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_line_id")
    private Long itemLineId;

    @Column(name = "item_id")
    private Long itemId;
}
