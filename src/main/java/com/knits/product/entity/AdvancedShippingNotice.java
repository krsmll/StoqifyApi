package com.knits.product.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "advanced_shipping_notice")
public class AdvancedShippingNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinTable(name = "advanced_shipping_notice_from_facility", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private Facility fromFacility;

    @ManyToOne
    @JoinTable(name = "advanced_shipping_notice_to_facility", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private Facility toFacility;

    @ManyToOne
    @JoinTable(name = "advanced_shipping_notice_supplier", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Company supplier;

    @ManyToOne
    @JoinTable(name = "advanced_shipping_notice_customer", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Company customer;

    @ManyToOne
    @JoinTable(name = "advanced_shipping_notice_driver", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_carrier_id"))
    private DriverCarrier driver;

    @ManyToOne
    @JoinTable(name = "advanced_shipping_notice_trailer", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "trailer_id"))
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
