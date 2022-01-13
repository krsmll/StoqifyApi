package com.knits.product.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "advanced_shipping_notice_driver")
public class AdvancedShippingNoticeDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "advanced_shipping_notice_id")
    private Long advancedShippingNoticeId;

    @Column(name = "driver_carrier_id")
    private Long driverId;
}
