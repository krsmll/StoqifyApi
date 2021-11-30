package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.List;

/**
 * This is an entity which is responsible to save and fetch user table data
 */
@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

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

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "group_id")
    private Long groupId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private Role role;

    /*@ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Group group;*/
}
