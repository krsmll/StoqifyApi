package com.knits.product.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
