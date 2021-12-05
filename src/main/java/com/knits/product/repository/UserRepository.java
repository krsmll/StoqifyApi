package com.knits.product.repository;

import java.util.List;
import java.util.Optional;
import com.knits.product.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLogin(String login);
    Optional<User> findOneByEmail(String email);
    List<User> findByLastNameStartsWithIgnoreCase(String keyword);
}
