package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.WarehouseDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/warehouse-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
public class WarehouseRestControllerTest {

    @Autowired
    private WarehouseRestController warehouseRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(warehouseRestController, "Warehouse Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/warehouse"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetById() throws Exception {
        String warehouseJson = new Gson().toJson(WarehouseDto.builder().id(3L).name("name3")
                .sortNumber("sort num 3").address("address3").commentToAddress("comment to addr 3")
                .comment("comment3").build());
        mockMvc.perform(get("/api/warehouse/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(warehouseJson));
    }

    @Test
    public void testCreate() throws Exception {
        WarehouseDto createdWarehouse = WarehouseDto.builder().id(4L).name("created")
                .sortNumber("created").address("created").commentToAddress("created")
                .comment("created").build();
        String createdWarehouseJson = new Gson().toJson(createdWarehouse);
        mockMvc.perform(post("/api/warehouse").contentType(MediaType.APPLICATION_JSON)
                .content(createdWarehouseJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdWarehouseJson));
        mockMvc.perform(get("/api/warehouse"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        WarehouseDto updatedWarehouse = WarehouseDto.builder().id(3L).name("updated")
                .sortNumber("updated").address("updated").commentToAddress("updated")
                .comment("updated").build();
        String updatedWarehouseJson = new Gson().toJson(updatedWarehouse);
        mockMvc.perform(put("/api/warehouse").contentType(MediaType.APPLICATION_JSON)
                .content(updatedWarehouseJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedWarehouseJson));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/warehouse/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/warehouse"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
