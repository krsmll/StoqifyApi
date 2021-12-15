package com.knits.product.controller;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import com.knits.product.dto.OrderLineDto;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

/**
 * This is a test class for Order line controller
 * @author Soumen Banerjee
 */
@SpringBootTest
@AutoConfigureMockMvc
class OrderLineControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    OrderLineDto[] getAllOrderLineList;

    @Test
    void test_to_get_all_order_lines_data() throws Exception {

        String getAllOrderLines = mockMvc.perform(MockMvcRequestBuilders.get("/api/orderline/all"))
                .andReturn().getResponse().getContentAsString();
        getAllOrderLineList = mapper.readValue(getAllOrderLines, OrderLineDto[].class);

        assertTrue(getAllOrderLineList.length > 0);
    }

    @Test
    void test_to_edit_a_single_order_line() throws Exception {

        String getAllOrderLines = mockMvc.perform(MockMvcRequestBuilders.get("/api/orderline/all"))
                .andReturn().getResponse().getContentAsString();
        getAllOrderLineList = mapper.readValue(getAllOrderLines, OrderLineDto[].class);

        Long getOrderLineDataId = Arrays.stream(getAllOrderLineList).findFirst().get().getId();
        OrderLineDto orderLineDto = new OrderLineDto(
                    getOrderLineDataId,
                    getOrderLineDataId,
                    3.99f,
                 100,
                100,
                  2);

        String updatedOrderLineData = mockMvc.perform(MockMvcRequestBuilders.put("/api/orderline")
                        .content(mapper.writeValueAsBytes(orderLineDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        OrderLineDto[] getUpdatedOrderLineDataList = mapper.readValue(updatedOrderLineData, OrderLineDto[].class);

        assertEquals(orderLineDto, Arrays.stream(getUpdatedOrderLineDataList)
                .filter(getOrderLineData -> getOrderLineData.getId().equals(getOrderLineDataId))
                .findAny().get());
    }
}