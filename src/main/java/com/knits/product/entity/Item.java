package com.knits.product.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "item_qty", nullable = false)
    private int quantity;

    @Column(name = "entered_at", nullable = false, updatable = false, insertable = false)
    private LocalDate enteredAt;

    @Column(name = "status", nullable = false)
    private String status;
}
