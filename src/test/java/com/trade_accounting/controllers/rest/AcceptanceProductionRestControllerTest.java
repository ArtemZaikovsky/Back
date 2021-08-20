package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.AcceptanceProductionDto;
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
@Sql(value = "/AcceptanceProduction-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
public class AcceptanceProductionRestControllerTest {

    @Autowired
    private AcceptanceProductionRestController acceptanceProductionRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(acceptanceProductionRestController, "Acceptance Production Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/acceptance/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getById() throws Exception {
        String acceptanceProductionDtoJson = new Gson().toJson(AcceptanceProductionDto.builder()
                .id(1L)
                .amount(2L)
                .productId(4L)
                .build());

        mockMvc.perform(get("/api/acceptance/product/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(acceptanceProductionDtoJson));
    }

    @Test
    void create() throws Exception {
        String acceptanceProductionDtoJson = new Gson().toJson(AcceptanceProductionDto.builder()
                .amount(8L)
                .productId(10L)
                .build());

        mockMvc.perform(post("/api/acceptance/product")
                        .contentType(MediaType.APPLICATION_JSON).content(acceptanceProductionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(acceptanceProductionDtoJson));

        mockMvc.perform(get("/api/acceptance/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void update() throws Exception {
        String acceptanceProductionDtoJson = new Gson().toJson(AcceptanceProductionDto.builder()
                .id(5L)
                .amount(555L)
                .productId(55L)
                .build());

        mockMvc.perform(put("/api/acceptance/product")
                        .contentType(MediaType.APPLICATION_JSON).content(acceptanceProductionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(acceptanceProductionDtoJson));

        mockMvc.perform(get("/api/acceptance/product/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(acceptanceProductionDtoJson));

        mockMvc.perform(get("/api/acceptance/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/acceptance/product/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/acceptance/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
