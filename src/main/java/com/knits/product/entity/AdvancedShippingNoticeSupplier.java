package com.knits.product.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "advanced_shipping_notice_supplier")
public class AdvancedShippingNoticeSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "advanced_shipping_notice_id")
    private Long advancedShippingNoticeId;

    @Column(name = "company_id")
    private Long companyId;
}
