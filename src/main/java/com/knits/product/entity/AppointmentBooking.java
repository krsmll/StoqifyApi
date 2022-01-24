package com.knits.product.entity;

import lombok.Data;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * This is a entity class to handle create an appointment with the carrier
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "appointment_booking")
public class AppointmentBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "operator_id")
    private Long operatorId;

    @Column(name = "carrier_id")
    private Long carrierId;

    @Column(name = "dock_id")
    private Long dockId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "appointment_booking_advanced_shipping", joinColumns = @JoinColumn(name = "advanced_shipping_notice_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_booking_id"))
    private AdvancedShippingNotice advancedShippingNotice;

    @Column(name = "status")
    private String status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_date")
    private Date createdData;

    @Column(name = "last_updated_date")
    private Date lastUpdated;
}
