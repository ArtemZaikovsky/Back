package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.DepartmentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    @RunWith(SpringRunner.class)
    @SpringBootTest
    @AutoConfigureMockMvc
    @TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
    @Sql(value = "/department-before.sql")
    @WithMockUser
    public class DepartmentRestControllerTest {

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(departmentRestController, "Department Rest controller is null");
    }
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/department"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
    @Test
    public void testGetById() throws Exception {
        String departmentJson = new Gson().toJson(DepartmentDto.builder().id(3L).name("name3")
                .sortNumber("sort num 3"));
        mockMvc.perform(get("/api/department/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(departmentJson));
    }
    @Test
    public void testCreate() throws Exception {
        DepartmentDto createdDepartment = DepartmentDto.builder().id(4L).name("created")
                .sortNumber("created").build();
        String createdDepartmentJson = new Gson().toJson(createdDepartment);
        mockMvc.perform(post("/api/department").contentType(MediaType.APPLICATION_JSON)
                .content(createdDepartmentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdDepartmentJson));
        mockMvc.perform(get("/api/department"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
    @Test
    public void testUpdate() throws Exception {
        DepartmentDto updatedDepartment = DepartmentDto.builder().id(3L).name("updated")
                .sortNumber("updated").build();
        String updatedDepartmentJson = new Gson().toJson(updatedDepartment);
        mockMvc.perform(put("/api/department").contentType(MediaType.APPLICATION_JSON)
                .content(updatedDepartmentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedDepartmentJson));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/department/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/department"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
