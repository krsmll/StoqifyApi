package com.knits.product.repository;

import java.util.List;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import com.knits.product.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
@Profile("integrationtest")
@RunWith(SpringRunner.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test_to_check_atleast_one_user_exists() {
        int totalUsersCount = userRepository.findAll().size();

        assertTrue(totalUsersCount > 0);
    }

    @Test
    void test_to_check_find_user_by_last_name() {
        List<User> getSearchedUsers = userRepository.findByLastNameStartsWithIgnoreCase("cri");

        assertTrue(getSearchedUsers.size() > 0);
    }

    @Test
    void test_to_remove_a_user_from_group() {
        User getSearchedUsers = userRepository.findAll().stream().filter(k -> k.getGroupId() != null)
                .collect(Collectors.toList()).get(0);
        Long Userid = getSearchedUsers.getId();

        userRepository.removeUserFromGroup(Userid);
    }

    @Test
    void test_to_check_by_adding_a_user_to_specific_group() {

    }

    @Test
    void addUserGroup() {
    }

    @Test
    void addUserRole() {
    }
}