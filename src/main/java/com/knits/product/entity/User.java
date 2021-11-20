package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * This is an entity which is responsible to save and fetch user table data
 */
@Entity
@Table(name = "user")
@Data
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", length = 50, unique = true, nullable = false)
    private String login;

    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 254, unique = true)
    private String email;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne
    private Group group;
}
