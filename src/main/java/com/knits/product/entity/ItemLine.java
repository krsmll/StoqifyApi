package com.knits.product.entity;

import lombok.Data;
import java.util.List;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * This entity is responsible for item line table
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "item_line")
public class ItemLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "item_link_item_line", joinColumns = @JoinColumn(name = "item_line_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    @Column(name = "entered_date")
    private Date enteredDate;

    @Column(name = "item_count")
    private Long itemCount;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "item_link_asn", joinColumns = @JoinColumn(name = "item_line_id"),
            inverseJoinColumns = @JoinColumn(name = "advanced_shipping_notice_id"))
    private AdvancedShippingNotice asn;

    @Column(name = "item_line_comment")
    private String comment;
}
