package com.knits.product.repository;

import com.knits.product.entity.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is purchase order repository interface
 * @author Soumen Banerjee
 */
public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine, Long> {
}
