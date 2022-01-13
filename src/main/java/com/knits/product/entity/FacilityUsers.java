package com.knits.product.entity;

import javax.persistence.*;


/**
 * This is a facility and user linker entity class to establish facility entity and user entity
 * @author Soumen Banerjee
 */
@Entity
@Table(name = "facility_user_link")
public class FacilityUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facility_id")
    private Long facilityId;

    @Column(name = "user_id")
    private Long userId;
}
