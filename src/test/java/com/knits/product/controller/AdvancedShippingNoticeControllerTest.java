package com.knits.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.product.dto.AdvancedShippingNoticeDto;
import com.knits.product.service.AdvancedShippingNoticeService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Test class for AdvancedShippingNoticeController.
 *
 * @author Kristjan Mill
 * @see AdvancedShippingNoticeController
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AdvancedShippingNoticeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdvancedShippingNoticeService advancedShippingNoticeService;

    private List<AdvancedShippingNoticeDto> testData;

    private final EasyRandom generator = new EasyRandom();

    @BeforeEach
    public void setup() {
        AdvancedShippingNoticeDto first = generator.nextObject(AdvancedShippingNoticeDto.class);
        AdvancedShippingNoticeDto second = generator.nextObject(AdvancedShippingNoticeDto.class);

        testData = List.of(first, second);
    }

    @Test
    public void test_get_all_advanced_shipping_notices() throws Exception {
        when(advancedShippingNoticeService.fetchAllAdvancedShippingNotices()).thenReturn(testData);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/advancedshippingnotice/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void test_get_advanced_shipping_notice_by_id() throws Exception {
        AdvancedShippingNoticeDto expected = testData.get(0);

        when(advancedShippingNoticeService.getAdvancedShippingNoticeById(expected.getId())).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/advancedshippingnotice/%s", expected.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expected.getId()), Long.class));
    }

    @Test
    public void test_create_new_advanced_shipping_notice_by_id() throws Exception {
        AdvancedShippingNoticeDto newAsn = generator.nextObject(AdvancedShippingNoticeDto.class);

        AdvancedShippingNoticeDto expected = new AdvancedShippingNoticeDto();
        expected.setId(1L);
        expected.setBillOfLandingNumber(newAsn.getBillOfLandingNumber());
        expected.setCustomer(newAsn.getCustomer());
        expected.setDeliveryDate(newAsn.getDeliveryDate());
        expected.setDriver(newAsn.getDriver());
        expected.setFromFacility(newAsn.getFromFacility());
        expected.setToFacility(newAsn.getToFacility());
        expected.setIdentificationNumber(newAsn.getIdentificationNumber());
        expected.setPackages(newAsn.getPackages());
        expected.setShipmentDate(newAsn.getShipmentDate());
        expected.setStatus(newAsn.getStatus());
        expected.setSupplier(newAsn.getSupplier());

        newAsn.setId(0L);

        when(advancedShippingNoticeService.createAdvancedShippingNotice(newAsn)).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/advancedshippingnotice")
                        .content(objectMapper.writeValueAsString(newAsn)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(expected.getId()), Long.class))
                .andExpect(jsonPath("$.identificationNumber", is(expected.getIdentificationNumber())));
    }
}
