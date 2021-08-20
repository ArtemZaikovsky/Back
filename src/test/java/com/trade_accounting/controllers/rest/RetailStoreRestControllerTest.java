package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.services.interfaces.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/RetailStore-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
public class RetailStoreRestControllerTest {

    @Autowired
    RetailStoreRestController retailStoreRestController;

    @Autowired
    CompanyService companyService;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(retailStoreRestController, "RetailStore Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/retail_stores"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testGetById() throws Exception {
        RetailStoreDto retailStoreDto = RetailStoreDto.builder()
                .id(1L)
                .activityStatus("Был в сети вчера")
                .defaultTaxationSystem("ОСН")
                .isActive(true)
                .name("Ozon")
                .orderTaxationSystem("УСН. Доход")
                .revenue(BigDecimal.valueOf(12000))
                .salesInvoicePrefix("SI")
                .companyId(1L)
                .build();

        String retailStroreDtoJson = new Gson().toJson(retailStoreDto);

        mockMvc.perform(get("/api/retail_stores/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailStroreDtoJson));
    }

    @Test
    void testCreate() throws Exception {
        RetailStoreDto retailStoreDto = RetailStoreDto.builder()
                .id(4L)
                .activityStatus("Был в сети вчера")
                .defaultTaxationSystem("ОСН")
                .isActive(true)
                .name("Ozon111")
                .orderTaxationSystem("УСН. Доход")
                .revenue(BigDecimal.valueOf(12000))
                .salesInvoicePrefix("SI")
                .companyId(4L)
                .cashiersIds(List.of(1L, 2L, 3L))
                .build();

        String retailStoreDtoJson = new Gson().toJson(retailStoreDto);

        mockMvc.perform(post("/api/retail_stores").contentType(MediaType.APPLICATION_JSON).content(retailStoreDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailStoreDtoJson));

        mockMvc.perform(get("/api/retail_stores"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        RetailStoreDto retailStoreDto = RetailStoreDto.builder()
                .id(3L)
                .activityStatus("Был в сети вчера")
                .defaultTaxationSystem("ОСН")
                .isActive(true)
                .name("Ozon111")
                .orderTaxationSystem("УСН. Доход")
                .revenue(BigDecimal.valueOf(12000))
                .salesInvoicePrefix("SI")
                .companyId(4L)
                .cashiersIds(List.of(1L, 2L, 3L))
                .build();

        String retailStoreDtoJson = new Gson().toJson(retailStoreDto);

        mockMvc.perform(put("/api/retail_stores").contentType(MediaType.APPLICATION_JSON).content(retailStoreDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailStoreDtoJson));

        mockMvc.perform(get("/api/retail_stores"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/retail_stores/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        mockMvc.perform(get("/api/retail_stores"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
