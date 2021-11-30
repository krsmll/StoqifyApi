package com.knits.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_role")
public class UserRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_role")
    private Long userRoleId;
}
