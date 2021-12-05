package com.knits.product.repository;

import java.util.List;
import org.junit.jupiter.api.Test;
import com.knits.product.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

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
}