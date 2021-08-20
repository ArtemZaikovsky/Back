package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.MovementProductDto;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/MovementProduct-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
public class MovementProductRestControllerTest {

    @Autowired
    private MovementProductRestController movementProductRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(movementProductRestController, "Movement Product Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/movement/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testGetById() throws Exception {
        String movementProductJson = new Gson().toJson(MovementProductDto.builder()
                .id(2L)
                .productId(2L)
                .amount(new BigDecimal("13.00"))
                .price(new BigDecimal("14.00"))
                .build());
        mockMvc.perform(get("/api/movement/product/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(movementProductJson));
    }

    @Test
    void testCreate() throws Exception {
        MovementProductDto movementProductDto = MovementProductDto.builder()
                .id(5L)
                .productId(5L)
                .amount(new BigDecimal("100.00"))
                .price(new BigDecimal("200.00")).build();
        String movementProductDtoJson = new Gson().toJson(movementProductDto);
        mockMvc.perform(post("/api/movement/product")
                .contentType(MediaType.APPLICATION_JSON).content(movementProductDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(movementProductDtoJson));
        mockMvc.perform(get("/api/movement/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void testUpdate() throws Exception {
        MovementProductDto movementProductDtoUpdate = MovementProductDto.builder()
                .id(2L).productId(8L).price(new BigDecimal("888.00"))
                .amount(new BigDecimal("999.00")).build();
        String movementProductDtoJsonUpdate = new Gson().toJson(movementProductDtoUpdate);
        mockMvc.perform(put("/api/movement/product").contentType(MediaType.APPLICATION_JSON)
                .content(movementProductDtoJsonUpdate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(movementProductDtoJsonUpdate));
        mockMvc.perform(get("/api/movement/product"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/movement/product/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/movement/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
