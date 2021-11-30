package com.knits.product;

import com.knits.product.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
@Qualifier
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
    Optional<User> findOneByEmail(String email);
    List<User> findByLastNameStartsWithIgnoreCase(String keyword);

    @Modifying
    @Query(value = "update users set group_id = ?2 where id = ?1", nativeQuery = true)
    int addUserGroup(Long userId, Long groupId);

    @Modifying
    @Query(value = "update users set role_id = ?2 where id = ?1", nativeQuery = true)
    int addUserRole(Long userId, Long roleId);

    @Modifying
    @Query(value = "update users set group_id = 0 where id = ?1", nativeQuery = true)
    int removeUserFromGroup(Long userId);
}
