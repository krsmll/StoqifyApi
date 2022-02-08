package com.knits.product.controller;

import org.junit.jupiter.api.Test;
import com.knits.product.dto.TrailerDto;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

/**
 * This is a test class for trailer controller
 * @author Soumen Banerjee
 */
@SpringBootTest
@AutoConfigureMockMvc
class TrailerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void test_to_get_all_trailers() throws Exception {

        String allTrailers = mockMvc.perform(MockMvcRequestBuilders.get("/api/trailer"))
                .andReturn().getResponse().getContentAsString();

        TrailerDto[] trailerDtos = mapper.readValue(allTrailers, TrailerDto[].class);

        assertTrue(trailerDtos.length > 1);
    }

    @Test
    void test_to_get_single_trailer_data() throws Exception {
        String allTrailers = mockMvc.perform(MockMvcRequestBuilders.get("/api/trailer/1"))
                .andReturn().getResponse().getContentAsString();

        assertTrue(allTrailers.length() > 0);
    }
}