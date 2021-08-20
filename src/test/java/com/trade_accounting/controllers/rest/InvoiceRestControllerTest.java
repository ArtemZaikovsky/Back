package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.WarehouseDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/Invoice-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@RequiredArgsConstructor
public class InvoiceRestControllerTest {

    @Autowired
    private InvoiceRestController invoiceRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(invoiceRestController, "Invoice Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getById() throws Exception {
        String invoiceDtoJson = new Gson().toJson(InvoiceDto.builder()
                .id(1L)
                .comment("comment 1")
                .date("2222-11-01T00:01:00")
                .isSpend(false)
                .typeOfInvoice("EXPENSE")
                .companyId(1L)

                .contractorId(1L)
                .warehouseId(1L)
                .build());

        mockMvc.perform(get("/api/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson));
    }

    @Test
    void create() throws Exception {
        String invoiceDtoJson = new Gson().toJson(InvoiceDto.builder()
                .id(2L)
                .comment("comment 2")
                .date("2222-11-01T00:01:00")
                .typeOfInvoice("RECEIPT")
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .isSpend(false)
                .build());
        mockMvc.perform(post("/api/invoice")
                        .contentType(MediaType.APPLICATION_JSON).content(invoiceDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson));

        mockMvc.perform(get("/api/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void update() throws Exception {
        String invoiceDtoJson = new Gson().toJson(InvoiceDto.builder()
                .id(3L)
                .isSpend(false)
                .comment("comment 3")
                .date("2222-11-01T00:03:00")
                .typeOfInvoice("RECEIPT")
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .build());

        mockMvc.perform(put("/api/invoice")
                        .contentType(MediaType.APPLICATION_JSON).content(invoiceDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson));
        mockMvc.perform(get("/api/invoice"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/invoice/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
