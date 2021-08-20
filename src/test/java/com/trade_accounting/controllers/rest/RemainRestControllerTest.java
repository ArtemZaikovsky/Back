package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.RemainDto;
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
@Sql(value = "/remain-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")

public class RemainRestControllerTest {
    @Autowired
    private RemainRestController remainRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(remainRestController, "Remain Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/remain"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetById() throws Exception {
        String remainJson = new Gson().toJson(RemainDto.builder().id(3L).name("name3")
                .reserve(3865).salesCost(8534).salesSum(4634)
                .sumOfCostPrice(75625).vendorCode("code3")
                .build());
        mockMvc.perform(get("/api/remain/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(remainJson));
    }

    @Test
    public void testCreate() throws Exception {
        RemainDto createdRemain = RemainDto.builder().id(4L).name("created")
                .reserve(1).salesCost(1).salesSum(1)
                .sumOfCostPrice(1).vendorCode("created")
                .build();
        String createdRemainJson = new Gson().toJson(createdRemain);
        mockMvc.perform(post("/api/remain").contentType(MediaType.APPLICATION_JSON)
                .content(createdRemainJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdRemainJson));
        mockMvc.perform(get("/api/remain"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        RemainDto updatedRemain = RemainDto.builder().id(3L).name("updated")
                .reserve(2).salesCost(2).salesSum(2)
                .sumOfCostPrice(2).vendorCode("updated")
                .build();
        String updatedRemainJson = new Gson().toJson(updatedRemain);
        mockMvc.perform(put("/api/remain").contentType(MediaType.APPLICATION_JSON)
                .content(updatedRemainJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedRemainJson));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/remain/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/remain"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
