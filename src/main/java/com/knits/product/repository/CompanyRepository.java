package com.knits.product.repository;

import java.util.Optional;
import com.knits.product.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByRegistryCode(String registerCode);
}
