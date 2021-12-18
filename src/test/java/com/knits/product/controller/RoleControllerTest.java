package com.knits.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.product.dto.RoleDto;
import com.knits.product.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    RoleRepository repository;

    @Test
    void test_to_get_all_roles() throws Exception {

        String getAllRoleControllerResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/roles/all")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        RoleDto[] getRoles = objectMapper.readValue(getAllRoleControllerResult, RoleDto[].class);

        assertTrue(getRoles.length > 1);
    }


    @Test
    void test_to_get_single_role_by_id() throws Exception {
        String getAllRoleControllerResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/roles/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        RoleDto getRole = objectMapper.readValue(getAllRoleControllerResult, RoleDto.class);

        assertEquals("Administrator", getRole.getRoleName());
        assertEquals(1, getRole.getId());

    }


    @Test
    void test_to_create_new_role() throws Exception {



        RoleDto role = new RoleDto();
        role.setRoleName("Test");

        System.out.println(role);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"roleName\": \"Test\"}"))
                .andExpect(status().isOk());
    }


    @Test
    void test_delete_roles_by_id_returns_() throws Exception {

        MvcResult getAllRoleControllerResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/roles/4")).andReturn();
        int status = getAllRoleControllerResult.getResponse().getStatus();
        String content = getAllRoleControllerResult.getResponse().getContentAsString();
        assertEquals(content, "Deleted role " + 4);

        assertEquals(200, status);
    }


}