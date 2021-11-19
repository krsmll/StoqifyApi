package com.knits.product.repository;

import com.knits.product.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByNameContainingIgnoreCase(String name);
    List<Group> findByIsActiveFalse();
    List<Group> findByIsActiveTrue();
}
