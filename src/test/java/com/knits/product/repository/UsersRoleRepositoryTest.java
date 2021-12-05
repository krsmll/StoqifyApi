package com.knits.product.repository;

import java.util.List;
import org.junit.jupiter.api.Test;
import com.knits.product.entity.UsersRole;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class UsersRoleRepositoryTest {

    @Autowired
    UsersRoleRepository usersRoleRepository;

    @Test
    void test_to_check_by_adding_a_user_to_specific_role() {
        List<UsersRole> getUsersRolesList = usersRoleRepository.findAll();
        UsersRole getSingleRoleData = getUsersRolesList.get(0);

        usersRoleRepository.delete(getSingleRoleData);

        assertNotEquals(getUsersRolesList.size(), usersRoleRepository.findAll());

        usersRoleRepository.save(new UsersRole(0L, getSingleRoleData.getRoleId(), getSingleRoleData.getUserId()));

        assertEquals(getUsersRolesList.size(), usersRoleRepository.findAll().size());
    }
}