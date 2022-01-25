package com.knits.product.entity;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment_booking_advanced_shipping")
public class AppointBookingWithAdvancedShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_booking_id")
    private Long appointmentBookingId;

    @Column(name = "advanced_shipping_notice_id")
    private Long advancedShippingNotice;
}
