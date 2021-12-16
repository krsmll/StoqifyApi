package com.knits.product.repository;

import com.knits.product.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    Optional<Facility> findByNameStartsWithIgnoreCase(String facilityName);
}
