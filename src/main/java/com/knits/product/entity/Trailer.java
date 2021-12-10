package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * This is an entity class to hold data from database via jpa
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "trailer")
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "licence_number")
    private String licenceNumber;

    @Column(name = "carrier")
    private String carrier;
}
