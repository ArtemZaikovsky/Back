package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.CorrectionProductDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/CorrectionProduct-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
class CorrectionProductRestControllerTest {

    @Autowired
    private CorrectionProductRestController correctionProductRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(correctionProductRestController, "Correction Product Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/correction/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testGetById() throws Exception {
        String correctionProductJson = new Gson().toJson(CorrectionProductDto.builder()
                .id(2L)
                .productId(2L)
                .amount(new BigDecimal("13.00"))
                .price(new BigDecimal("14.00"))
                .build());
        mockMvc.perform(get("/api/correction/product/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(correctionProductJson));
    }

    @Test
    void testCreate() throws Exception {
        CorrectionProductDto correctionProductDto = CorrectionProductDto.builder()
                .id(5L)
                .productId(5L)
                .amount(new BigDecimal("100.00"))
                .price(new BigDecimal("200.00")).build();
        String correctionProductDtoJson = new Gson().toJson(correctionProductDto);
        mockMvc.perform(post("/api/correction/product")
                .contentType(MediaType.APPLICATION_JSON).content(correctionProductDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(correctionProductDtoJson));
        mockMvc.perform(get("/api/correction/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void testUpdate() throws Exception {
        CorrectionProductDto correctionProductDtoUpdate = CorrectionProductDto.builder()
                .id(2L).productId(8L).price(new BigDecimal("888.00"))
                .amount(new BigDecimal("999.00")).build();
        String correctionProductDtoJsonUpdate = new Gson().toJson(correctionProductDtoUpdate);
        mockMvc.perform(put("/api/correction/product").contentType(MediaType.APPLICATION_JSON)
                .content(correctionProductDtoJsonUpdate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(correctionProductDtoJsonUpdate));
        mockMvc.perform(get("/api/correction/product"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/correction/product/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/correction/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
