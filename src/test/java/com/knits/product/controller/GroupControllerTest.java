package com.knits.product.controller;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.product.dto.GroupDto;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test_to_get_all_groups() throws Exception {

        String getGroupControllerResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/groups/all"))
                .andReturn().getResponse().getContentAsString();

        GroupDto[] getGroups = objectMapper.readValue(getGroupControllerResult, GroupDto[].class);

        assertTrue(getGroups.length > 1);
    }

    @Test
    void test_to_get_single_group_data() throws Exception {
        String getGroupControllerResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/groups/1"))
                .andReturn().getResponse().getContentAsString();

        GroupDto getGroupName = objectMapper.readValue(getGroupControllerResult, GroupDto.class);

        assertEquals("Admin", getGroupName.getName());
    }

    @Test
    void test_active_groups() throws Exception {
        String getGroupControllerResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/groups/active"))
                .andReturn().getResponse().getContentAsString();

        GroupDto[] getActiveGroups = objectMapper.readValue(getGroupControllerResult, GroupDto[].class);

        assertEquals(true, Arrays.stream(getActiveGroups).findFirst().get().getIsActive());
        assertEquals("Admin", Arrays.stream(getActiveGroups).findFirst().get().getName());
    }

    @Test
    void test_to_get_group_by_id() throws Exception {
        String getGroupControllerResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/groups/1"))
                .andReturn().getResponse().getContentAsString();

        GroupDto getGroupData = objectMapper.readValue(getGroupControllerResult, GroupDto.class);

        assertEquals(true, getGroupData.getIsActive());
        assertEquals("Admin",getGroupData.getName());
    }
}