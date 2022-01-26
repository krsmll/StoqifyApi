package com.knits.product.repository;

import com.knits.product.entity.ItemLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemLineRepository extends JpaRepository<ItemLine, Long> { }
