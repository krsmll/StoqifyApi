package com.knits.product.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_group")
public class UsersGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "user_id")
    private Long userId;
}