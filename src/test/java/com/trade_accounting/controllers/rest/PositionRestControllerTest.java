package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.PositionDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Stanislav Dusiak
 * @since 06.08.2021
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/Position-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithMockUser
@RequiredArgsConstructor
class PositionRestControllerTest {

    @Autowired
    private PositionRestController positionRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(positionRestController, "Position Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/position"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getById() throws Exception {
        String PositionDtoJson = new Gson().toJson(PositionDto.builder()
                .id(1L)
                .name("Stanislav")
                .sortNumber("1")
                .build());

        mockMvc.perform(get("/api/position/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(PositionDtoJson));
    }

    @Test
    void create() throws Exception {
        String PositionDtoJson = new Gson().toJson(PositionDto.builder()
                .id(1L)
                .name("Stanislav")
                .sortNumber("2")
                .build()
        );

        mockMvc.perform(post("/api/position")
                        .contentType(MediaType.APPLICATION_JSON).content(PositionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(PositionDtoJson));

        mockMvc.perform(get("/api/position"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void update() throws Exception {
        String PositionDtoJson = new Gson().toJson(PositionDto.builder()
                .id(1L)
                .name("Stanislav")
                .sortNumber("2")
                .build()
        );

        mockMvc.perform(put("/api/position")
                        .contentType(MediaType.APPLICATION_JSON).content(PositionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(PositionDtoJson));
        mockMvc.perform(get("/api/position"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/position/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/position"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}