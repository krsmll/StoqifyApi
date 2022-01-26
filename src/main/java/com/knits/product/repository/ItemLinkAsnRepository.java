package com.knits.product.repository;

import com.knits.product.entity.ItemLinkAsn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemLinkAsnRepository extends JpaRepository<ItemLinkAsn, Long> {
    Optional<ItemLinkAsn> findByItemLineId(Long itemLineId);
}
