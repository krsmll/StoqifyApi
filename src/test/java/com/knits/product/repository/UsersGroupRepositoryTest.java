package com.knits.product.repository;

import java.util.List;
import org.junit.jupiter.api.Test;
import com.knits.product.entity.UsersGroup;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class UsersGroupRepositoryTest {

    @Autowired
    UserGroupRepository userGroupRepository;

    @Test
    void test_to_add_and_remove_a_user_from_group() {
        List<UsersGroup> getAllUsersGroupList = userGroupRepository.findAll();
        int totalRecord = getAllUsersGroupList.size();
        UsersGroup getSingleUsersGroupMappingData = getAllUsersGroupList.get(0);

        userGroupRepository.delete(getAllUsersGroupList.get(0));

        assertNotEquals(totalRecord, userGroupRepository.findAll());

        userGroupRepository.save(new UsersGroup(0L, 2L, getSingleUsersGroupMappingData.getUserId()));

        assertEquals(totalRecord, userGroupRepository.findAll().size());
    }
}