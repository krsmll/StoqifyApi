package com.knits.product.repository;

import java.util.List;
import com.knits.product.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByNameContainingIgnoreCase(String name);
    List<Group> findByIsActiveFalse();
    List<Group> findByIsActiveTrue();
    List<Group> findByName(String groupName);
}
