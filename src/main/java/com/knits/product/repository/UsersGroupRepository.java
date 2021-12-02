package com.knits.product.repository;

import com.knits.product.entity.UsersGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersGroupRepository extends JpaRepository<UsersGroup, Long> {

    UsersGroup findOneByUserId(Long userId);
    UsersGroup findOneByGroupId(Long groupId);
}
