package com.knits.product.entity;

import lombok.*;

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

    @Column(name = "shipment_date", columnDefinition = "TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private Date shipmentDate;

    @Column(name = "identification_number")
    private String identificationNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "advanced_shipping_notice_purchase_order", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "purchase_order_id"))
    private List<OrderLine> orderLines;
}
