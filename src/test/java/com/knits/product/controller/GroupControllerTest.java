package com.knits.product.controller;


import com.knits.product.dto.GroupDto;
import com.knits.product.dto.UserDto;
import com.knits.product.entity.Group;
import com.knits.product.mapper.GroupMapper;
import com.knits.product.repository.GroupRepository;
import com.knits.product.service.GroupService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GroupController.class)
class GroupControllerTest {

    @Autowired
    MockMvc mockMvc;


    @MockBean
    private GroupController groupController;


    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private GroupMapper groupMapper;


    //Testing Get all groups URL
    @Test
    void testShouldGetAllGroup() throws Exception {

        GroupDto group = new GroupDto();
        group.setId(1);
        group.setName("Admin");

        when(groupService.getGroupById(1)).thenReturn(group);
        System.out.println(group);

        String URL = "/api/groups/1";
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("@.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(status().isOk());

    }


    @Test
    void testShouldReturnOkNoContent() throws Exception {

    }


}