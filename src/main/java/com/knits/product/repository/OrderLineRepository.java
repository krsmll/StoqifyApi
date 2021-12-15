package com.knits.product.repository;

import com.knits.product.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
