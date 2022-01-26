package com.knits.product.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_link_item_line")
public class ItemLinkItemLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_line_id")
    private Long itemLineId;

    @Column(name = "item_id")
    private Long itemId;
}
