package com.knits.product.repository;

import java.util.List;
import com.knits.product.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastNameStartsWithIgnoreCase(String keyword);
}
