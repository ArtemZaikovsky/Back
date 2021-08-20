package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.BalanceAdjustmentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@WithUserDetails(value = "karimogon@mail.ru")
@Sql(value = "/BalanceAdjustment-before.sql")
public class BalanceAdjustmentRestControllerTest {

    @Autowired
    ReturnToSupplierRestController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(controller, "BalanceAdjustmentRestController Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/balanceAdjustment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testGetById() throws Exception {
        String balanceAdjustmentJson = new Gson().toJson(BalanceAdjustmentDto.builder()
                .id(1L)
                .date("2021-06-23 15:10")
                .comment("Комментарий 1")
                .companyId(1L)
                .contractorId(1L)
                .account("Счет 1")
                .cashOffice("Касса 1")
                .comment("Комм 1")
                .dateChanged("2021-06-23 15:10")
                .whoChanged("1")
                .build());
        mockMvc.perform(get("/api/balanceAdjustment/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(balanceAdjustmentJson));
    }

    @Test
    public void testCreate() throws Exception {
        String balanceAdjustmentJson = new Gson().toJson(BalanceAdjustmentDto.builder()
                .id(2L)
                .date("2021-06-23 15:10")
                .comment("Комментарий 2")
                .companyId(2L)
                .contractorId(2L)
                .account("Счет 2")
                .cashOffice("Касса 2")
                .comment("Комм 2")
                .dateChanged("2021-06-23 15:10")
                .whoChanged("2")
                .build());
        mockMvc.perform(post("/api/balanceAdjustment").contentType(MediaType.APPLICATION_JSON)
                .content(balanceAdjustmentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(balanceAdjustmentJson));
        mockMvc.perform(get("/api/balanceAdjustment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        String balanceAdjustmentJson = new Gson().toJson(BalanceAdjustmentDto.builder()
                .id(3L)
                .date("2021-06-23 15:10")
                .comment("Комментарий 3")
                .companyId(3L)
                .contractorId(3L)
                .account("Счет 3")
                .cashOffice("Касса 3")
                .comment("Комм 3")
                .dateChanged("2021-06-23 15:10")
                .whoChanged("3")
                .build());
        mockMvc.perform(put("/api/balanceAdjustment").contentType(MediaType.APPLICATION_JSON)
                .content(balanceAdjustmentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(balanceAdjustmentJson));
        mockMvc.perform(get("/api/balanceAdjustment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/balanceAdjustment/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/balanceAdjustment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
