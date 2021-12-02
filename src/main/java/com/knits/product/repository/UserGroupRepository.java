package com.knits.product.repository;

import java.util.Optional;
import com.knits.product.entity.UsersGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UsersGroup, Long> {

    Optional<UsersGroup> findOneByUserId(Long userId);
}
