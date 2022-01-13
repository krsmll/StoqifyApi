package com.knits.product.entity;

import lombok.Data;
import java.util.List;
import javax.persistence.*;

/**
 * This is an facility entity class and it's responsible to handle database data via JPA
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private Long zipCode;

    @Column(name = "street")
    private String street;

    @Column(name = "ing_ltd")
    private String ingLtd;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "facility_user_link", joinColumns = @JoinColumn(name = "facility_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
