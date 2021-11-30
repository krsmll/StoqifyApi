package com.knits.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_role")
public class UsersRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;
}
