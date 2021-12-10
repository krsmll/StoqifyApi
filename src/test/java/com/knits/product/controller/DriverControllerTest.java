package com.knits.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.product.dto.DriverDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DriverControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test_to_get_all_drivers() throws Exception {

        String driverList = mockMvc.perform(MockMvcRequestBuilders.get("/api/driver/all"))
                .andReturn().getResponse().getContentAsString();

        DriverDto[] getAllDrivers = objectMapper.readValue(driverList, DriverDto[].class);

        assertTrue(getAllDrivers.length > 1);
    }

    @Test
    void test_to_get_driver_data() throws Exception {
        String driverList = mockMvc.perform(MockMvcRequestBuilders.get("/api/driver/1"))
                .andReturn().getResponse().getContentAsString();

        DriverDto getSingleDriverData = objectMapper.readValue(driverList, DriverDto.class);

        assertEquals("Rheta", getSingleDriverData.getFirstName());
    }
}