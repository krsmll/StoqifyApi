package com.knits.product.repository;

import com.knits.product.entity.User;
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
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
    Optional<User> findOneByEmail(String email);
    List<User> findByLastNameStartsWith(String keyword);

    @Modifying
    @Query(value = "update user set group_id = ?2 where id = ?1", nativeQuery = true)
    int addUserGroup(Integer userId, Integer groupId);

    @Modifying
    @Query(value = "update user set role_id = ?2 where id = ?1", nativeQuery = true)
    int addUserRole(Integer userId, Integer roleId);
}
