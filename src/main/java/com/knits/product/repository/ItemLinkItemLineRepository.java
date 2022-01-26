package com.knits.product.repository;

import com.knits.product.entity.ItemLinkItemLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemLinkItemLineRepository extends JpaRepository<ItemLinkItemLine, Long> {
    List<ItemLinkItemLine> findByItemLineId(Long itemLineId);
}
