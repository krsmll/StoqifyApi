package com.knits.product.repository;

import com.knits.product.entity.DriverCarrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverCarrier, Long> {
}
