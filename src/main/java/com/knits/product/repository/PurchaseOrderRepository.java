package com.knits.product.repository;

import com.knits.product.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is purchase order repository interface
 * @author Soumen Banerjee
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
}
