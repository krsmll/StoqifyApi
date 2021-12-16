package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;

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
}
