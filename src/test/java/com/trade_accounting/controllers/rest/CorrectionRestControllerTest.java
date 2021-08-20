package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.CorrectionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
@Sql(value = "/Correction-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
class CorrectionRestControllerTest {

    @Autowired
    private CorrectionRestController correctionRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(correctionRestController, "Correction Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/correction"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testGetById() throws Exception {
        CorrectionDto correctionDto = CorrectionDto.builder()
                .id(1L)
                .date("2021-06-23 15:10")
                .companyId(1L)
                .warehouseId(1L)
                .isSent(false).isPrint(false).writeOffProduct(false)
                .comment("Оприходование 1")
                .correctionProductIds(List.of(1L, 2L, 3L)).build();

        String correctionDtoJson = new Gson().toJson(correctionDto);

        mockMvc.perform(get("/api/correction/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(correctionDtoJson));
    }

    @Test
    void testCreate() throws Exception {
        CorrectionDto correctionDto = CorrectionDto.builder()
                .id(4L)
                .date("2021-06-23 15:10")
                .warehouseId(1L)
                .companyId(5L)
                .isSent(false)
                .isPrint(false)
                .writeOffProduct(false)
                .comment("Оприходование 4")
                .correctionProductIds(List.of(10L, 11L, 12L))
                .build();
        String correctionDtoJson = new Gson().toJson(correctionDto);
        mockMvc.perform(post("/api/correction")
                .contentType(MediaType.APPLICATION_JSON).content(correctionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(correctionDtoJson));
        mockMvc.perform(get("/api/correction"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        CorrectionDto correctionDtoUpdate = CorrectionDto.builder()
                .id(3L)
                .date("2021-06-23 15:10")
                .warehouseId(1L)
                .companyId(7L)
                .isSent(false)
                .isPrint(false)
                .writeOffProduct(false)
                .comment("Оприходование 3 UPDATE")
                .correctionProductIds(List.of(7L, 8L, 9L))
                .build();
        String correctionDtoJson = new Gson().toJson(correctionDtoUpdate);
        mockMvc.perform(put("/api/correction")
                .contentType(MediaType.APPLICATION_JSON).content(correctionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(correctionDtoJson));
        mockMvc.perform(get("/api/correction"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/correction/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/correction"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
