package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * This is a entity class to handle data from database
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "registry_code", nullable = false)
    private String registryCode;

    @Column(name = "vat_code", nullable = false)
    private String vatCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "zip_code", nullable = false)
    private Long zipCode;

    @Column(name = "street",  nullable = false)
    private String street;

    @Column(name = "logo")
    private String logo;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "company_type")
    private String companyType;
}
