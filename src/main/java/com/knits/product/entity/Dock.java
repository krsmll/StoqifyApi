package com.knits.product.entity;

import lombok.Data;
import java.util.Date;
import javax.persistence.*;

/**
 * This is dock entity class to handle data with database
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "dock")
public class Dock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supervisor_id", nullable = false)
    private Long supervisorId;

    @Column(name = "dock_dimentation", nullable = false)
    private Long dockDimentaion;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_on", nullable = false)
    private Date created;

    @Column(name = "item_count", nullable = false)
    private Integer itemCount;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "pre_registration")
    private Date preRegistration;

    @Column(name = "last_update")
    private Date lastUpdated;
}
