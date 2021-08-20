package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.models.dto.TechnicalCardProductionDto;
import com.trade_accounting.services.impl.TechnicalCardServiceImpl;
import com.trade_accounting.services.interfaces.TechnicalCardGroupService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/TechnicalCard-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
public class TechnicalCardRestControllerTest {

    @Autowired
    private TechnicalCardRestController technicalCardRestController;

    @Autowired
    private TechnicalCardServiceImpl technicalCardService;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(technicalCardRestController, "TechnicalCard Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testGetById() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(1L));

        mockMvc.perform(get("/api/technical_card/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));
    }

    @Test
    void testCreate() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(2L));

        mockMvc.perform(post("/api/technical_card").contentType(MediaType.APPLICATION_JSON)
                .content(technicalCardDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(3L));

        mockMvc.perform(put("/api/technical_card").contentType(MediaType.APPLICATION_JSON).content(technicalCardDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/technical_card/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
