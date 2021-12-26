package com.knits.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "facility_user_link")
public class FacilityUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facility_id")
    private Long facilityId;

    @Column(name = "user_group_id")
    private Long userId;
}
