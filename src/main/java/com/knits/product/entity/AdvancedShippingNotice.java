package com.knits.product.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity class representing an advanced shipping notice.
 *
 * @see com.knits.product.controller.AdvancedShippingNoticeController AdvancedShippingNoticeController
 * @see com.knits.product.service.AdvancedShippingNoticeService AdvancedShippingNoticeService
 * @see com.knits.product.dto.AdvancedShippingNoticeDto AdvancedShippingNoticeDto
 */
@Data
@Entity
@Table(name = "advanced_shipping_notice")
public class AdvancedShippingNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_facility_id")
    private Facility fromFacility;

    @ManyToOne
    @JoinColumn(name = "to_facility_id")
    private Facility toFacility;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Company supplier;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Company customer;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverCarrier driver;

    @ManyToOne
    @JoinColumn(name = "trailer_id")
    private Trailer trailer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "advanced_shipping_notice_purchase_order", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "purchase_order_id"))
    private List<PurchaseOrder> packages;

    @Column(name = "bill_of_landing_number")
    private String billOfLandingNumber;

    @Column(name = "shipment_date", columnDefinition = "TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private Date shipmentDate;

    @Column(name = "delivery_date", columnDefinition = "TIMESTAMP")
    private Date deliveryDate;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "status")
    private String status;
}
