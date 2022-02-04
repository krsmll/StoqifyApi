package com.knits.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.product.dto.ItemDto;
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
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    RoleRepository repository;

//    @Test
//    void test_to_get_all_items() throws Exception {
//        String getAllItem = mockMvc
//                .perform(MockMvcRequestBuilders.get("/api/item/all")
//                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        ItemDto[] getItems = objectMapper.readValue(getAllItem, ItemDto[].class);
//
//        assertTrue(getItems.length > 1);
//
//    }

//    @Test
//    void test_to_search_by_item_name() throws Exception {
//        String getItems = mockMvc
//                .perform(MockMvcRequestBuilders.get("/api/search/Broadcom").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//        ItemDto getItem = objectMapper.readValue(getItems, ItemDto.class);
//
//        assertEquals("Broadcom", getItem.getName());
//        assertEquals(1, getItem.getId());
//
//    }


//    @Test
//    void test_to_create_new_item() throws Exception {
//
//        ItemDto item = new ItemDto();
//        item.setName("Test");
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/api/item")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\": \"Test\"}"))
//                .andExpect(status().isOk());
//    }

    @Test
    void test_delete_item_by_id() throws Exception {

        MvcResult getItems = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/item/5")).andReturn();
        int status = getItems.getResponse().getStatus();
        assertEquals(204, status);
    }

}