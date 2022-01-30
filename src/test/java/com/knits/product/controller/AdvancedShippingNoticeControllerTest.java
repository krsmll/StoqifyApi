package com.knits.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.product.dto.AdvancedShippingNoticeDto;
import com.knits.product.dto.PurchaseOrderDto;
import com.knits.product.service.AdvancedShippingNoticeService;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Integration test class for AdvancedShippingNotice functionality.
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

    @Autowired
    private AdvancedShippingNoticeService advancedShippingNoticeService;

    private final EasyRandom generator = initGenerator();

    @Test
    @Order(1)
    public void test_get_all_advanced_shipping_notices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/advancedshippingnotice/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void test_get_advanced_shipping_notice_by_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/advancedshippingnotice/%s", 1L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1L), Long.class));
    }

    @Test
    public void test_create_new_advanced_shipping_notice() throws Exception {
        long len = advancedShippingNoticeService.fetchAllAdvancedShippingNotices().size();

        AdvancedShippingNoticeDto newAsn = generator.nextObject(AdvancedShippingNoticeDto.class);

        AdvancedShippingNoticeDto expected = new AdvancedShippingNoticeDto();
        expected.setId(++len);
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
        expected.setTrailer(newAsn.getTrailer());
        expected.setSupplier(newAsn.getSupplier());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/advancedshippingnotice")
                        .content(objectMapper.writeValueAsString(newAsn))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(expected.getId()), Long.class))
                .andExpect(jsonPath("$.identificationNumber", is(expected.getIdentificationNumber())))
                .andExpect(jsonPath("$.status", is(expected.getStatus())))
                .andExpect(jsonPath("$.toFacility", notNullValue()))
                .andExpect(jsonPath("$.fromFacility", notNullValue()))
                .andExpect(jsonPath("$.customer", notNullValue()))
                .andExpect(jsonPath("$.driver", notNullValue()))
                .andExpect(jsonPath("$.trailer", notNullValue()))
                .andExpect(jsonPath("$.supplier", notNullValue()))
                .andExpect(jsonPath("$.packages.length()", is(expected.getPackages().size())));
    }

    @Test
    public void test_edit_advanced_shipping_notice() throws Exception {
        AdvancedShippingNoticeDto asn = advancedShippingNoticeService.getAdvancedShippingNoticeById(1);
        String oldIdNumber = asn.getIdentificationNumber();
        String oldStatus = asn.getStatus();
        asn.setIdentificationNumber("Test123");
        asn.setStatus("Test123");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/advancedshippingnotice")
                        .content(objectMapper.writeValueAsString(asn))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.identificationNumber", not(oldIdNumber)))
                .andExpect(jsonPath("$.identificationNumber", is("Test123")))
                .andExpect(jsonPath("$.status", not(oldStatus)))
                .andExpect(jsonPath("$.status", is("Test123")));
    }

    @Test
    public void test_add_packages_to_advanced_shipping_notice() throws Exception {
        List<PurchaseOrderDto> ordersToAdd = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ordersToAdd.add(generator.nextObject(PurchaseOrderDto.class));
        }

        AdvancedShippingNoticeDto asn = advancedShippingNoticeService.getAdvancedShippingNoticeById(1);
        int len = asn.getPackages().size();

        mockMvc.perform(MockMvcRequestBuilders.post(String.format("/api/advancedshippingnotice/%s/packages", asn.getId()))
                        .content(objectMapper.writeValueAsString(ordersToAdd))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.packages.length()", is(len + 10)));
    }

    @Test
    public void test_partially_edit_advanced_shipping_notice() throws Exception {
        AdvancedShippingNoticeDto asn = advancedShippingNoticeService.getAdvancedShippingNoticeById(1);
        String oldIdNumber = asn.getIdentificationNumber();
        String oldStatus = asn.getStatus();


        String newStatus = "New and Epic status";
        String newIdNumber = "New and Epic ID Number";

        AdvancedShippingNoticeDto partialDto = new AdvancedShippingNoticeDto();
        partialDto.setId(asn.getId());
        partialDto.setStatus(newStatus);
        partialDto.setIdentificationNumber(newIdNumber);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/advancedshippingnotice/")
                        .content(objectMapper.writeValueAsString(partialDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.identificationNumber", not(oldIdNumber)))
                .andExpect(jsonPath("$.identificationNumber", is(newIdNumber)))
                .andExpect(jsonPath("$.status", not(oldStatus)))
                .andExpect(jsonPath("$.status", is(newStatus)));
    }

    @Test
    public void test_remove_package_from_advanced_shipping_notice() throws Exception {
        AdvancedShippingNoticeDto asn = advancedShippingNoticeService.getAdvancedShippingNoticeById(1);
        List<PurchaseOrderDto> packages = asn.getPackages();
        int len = packages.size();
        long packageId = packages.get(0).getId();

        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/advancedshippingnotice/%s/packages/%s", asn.getId(), packageId)))
                .andExpect(jsonPath("$.packages.length()", is(--len)));
    }

    private static EasyRandom initGenerator() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.stringLengthRange(5, 7);
        parameters.collectionSizeRange(2, 3);
        parameters.randomize(Long.class, new LongRangeRandomizer(1L, 3L));
        parameters.randomize(Integer.class, new IntegerRangeRandomizer(1, 30));
        parameters.excludeField(FieldPredicates.named("id"));

        return new EasyRandom(parameters);
    }
}
