package com.knits.product.repository;

import com.knits.product.entity.Role;
import com.knits.product.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Role} entity.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}