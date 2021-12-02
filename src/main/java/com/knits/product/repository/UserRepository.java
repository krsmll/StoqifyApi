package com.knits.product.repository;

import java.util.List;
import java.util.Optional;
import com.knits.product.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
@Qualifier
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
    Optional<User> findOneByEmail(String email);
    List<User> findByLastNameStartsWithIgnoreCase(String keyword);
}
