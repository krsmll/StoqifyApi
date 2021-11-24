package com.knits.product.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long roleId;

    @Column(name = "role_name", nullable = false)
    private String roleName;
}
