package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.AcceptanceDto;
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
@Sql(value = "/acceptance-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")

public class AcceptanceRestControllerTest {
    @Autowired
    private AcceptanceRestController acceptanceRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(acceptanceRestController, "AcceptanceRestController is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/acceptance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetById() throws Exception {
        String acceptanceJson = new Gson().toJson(AcceptanceDto.builder()
                .id(3L)
                .comment("comment3")
                .incomingNumber("3")
                .incomingNumberDate("2021-07-03").contractorId(3L)
                .warehouseId(3L)
                .contractId(3L)
                .build());
        mockMvc.perform(get("/api/acceptance/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(acceptanceJson));
    }

    @Test
    public void testCreate() throws Exception {
        AcceptanceDto acceptanceDto = AcceptanceDto.builder()
                .id(4L)
                .comment("comment3")
                .incomingNumber("333")
                .incomingNumberDate("2021-07-03")
                .contractorId(4L)
                .warehouseId(4L)
                .contractId(4L)
                .build();
        String createdAcceptanceJson = new Gson().toJson(acceptanceDto);
        mockMvc.perform(post("/api/acceptance").contentType(MediaType.APPLICATION_JSON)
                .content(createdAcceptanceJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdAcceptanceJson));
        mockMvc.perform(get("/api/acceptance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        AcceptanceDto updatedAcceptance = AcceptanceDto.builder()
                .id(3L)
                .comment("comment3")
                .incomingNumber("333")
                .incomingNumberDate("2021-07-03").contractorId(3L)
                .warehouseId(3L)
                .contractId(3L)
                .build();
        String updatedAcceptanceJson = new Gson().toJson(updatedAcceptance);
        mockMvc.perform(put("/api/acceptance").contentType(MediaType.APPLICATION_JSON)
                .content(updatedAcceptanceJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedAcceptanceJson));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/acceptance/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/acceptance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
