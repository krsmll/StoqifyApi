package com.knits.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.product.dto.UserDto;
import com.knits.product.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test class for User controller
 * @author Soumen Banerjee
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void test_to_get_all_users() throws Exception {

        String getAllUsersData = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/all"))
                .andReturn().getResponse().getContentAsString();
        UserDto[] getAllUsersList = mapper.readValue(getAllUsersData, UserDto[].class);

        assertTrue(getAllUsersList.length > 0);
    }

    @Test
    void test_get_user_by_id() throws Exception {
        String getSingleUser = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andReturn().getResponse().getContentAsString();

        UserDto getSingleUserData = mapper.readValue(getSingleUser, UserDto.class);

        assertEquals("Rheta", getSingleUserData.getFirstName());
    }

}