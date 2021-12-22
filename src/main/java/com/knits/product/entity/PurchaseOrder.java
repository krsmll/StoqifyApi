package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * This is purchase order entitiy to handle data
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_company_id")
    private Long supplierCompanyId;

    @Column(name = "customer_comapny_id")
    private Long customerComapnyId;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "type")
    private String type;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @Column(name = "deliver_date")
    private LocalDate deliverDate;

    @Column(name = "cancel_date")
    private LocalDate cancelDate;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "purchase_order_line", joinColumns = @JoinColumn(name = "order_line_id"),
            inverseJoinColumns = @JoinColumn(name = "purchase_order_id"))
    private OrderLine orderLine;
}
