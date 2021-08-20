package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
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
@Sql(value = "/AttributeOfCalculationObject-before.sql")
public class AttributeOfCalculationObjectRestControllerTest {

    @Autowired
    AttributeOfCalculationObjectRestController attributeOfCalculationObjectRestController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testExistense()throws Exception{
        assertNotNull(attributeOfCalculationObjectRestController, "AttributeOfCalculationObject Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/attribute/calculation/object"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
    @Test
    public void testGetById() throws Exception {
        String attributeOfCalculationObjectJson = new Gson().toJson(AttributeOfCalculationObjectDto.builder().id(3L).name("name3")
                .isService(true)
                .sortNumber("sort num 3"));
        mockMvc.perform(get("/api/attribute/calculation/object/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(attributeOfCalculationObjectJson));
    }
    @Test
    public void testCreate() throws Exception {
        AttributeOfCalculationObjectDto createdAttributeOfCalculationObject = AttributeOfCalculationObjectDto.builder().id(4L).name("created")
                .isService(true)
                .sortNumber("created").build();
        String createdDepartmentJson = new Gson().toJson(createdAttributeOfCalculationObject);
        mockMvc.perform(post("/api/attribute/calculation/object").contentType(MediaType.APPLICATION_JSON)
                .content(createdDepartmentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdDepartmentJson));
        mockMvc.perform(get("/api/attribute/calculation/object"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
    @Test
    public void testUpdate() throws Exception {
        AttributeOfCalculationObjectDto updatedAttributeOfCalculationObject = AttributeOfCalculationObjectDto.builder().id(3L).name("updated")
                .isService(true)
                .sortNumber("updated").build();
        String updatedAttributeOfCalculationObjectJson = new Gson().toJson(updatedAttributeOfCalculationObject);
        mockMvc.perform(put("/api/attribute/calculation/object").contentType(MediaType.APPLICATION_JSON)
                .content(updatedAttributeOfCalculationObjectJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedAttributeOfCalculationObjectJson));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/attribute/calculation/object/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/attribute/calculation/object"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}