package com.knits.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_link_asn")
public class ItemLinkAsn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_line_id")
    private Long itemLineId;

    @Column(name = "advanced_shipping_notice_id")
    private Long advancedShippingNoticeId;
}
