package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;

import com.trade_accounting.models.dto.EmployeeDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/employee-before.sql")
@WithMockUser
public class EmployeeRestControllerTest {
    @Autowired
    EmployeeRestController employeeRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(employeeRestController, "Employee Rest controller is null");
    }
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
    @Test
    public void testGetById() throws Exception {
        String employeeJson = new Gson().toJson(EmployeeDto.builder().id(3L).lastName("last_name3")
                .firstName("first_name3")
                .middleName("middle_name3")
                .sortNumber("sort_number3")
                .phone("phone3")
                .inn("012345678903")
                .description("description3")
                .email("email3")
                .password("password3"));
        mockMvc.perform(get("/api/employee/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(employeeJson));
    }
    @Test
    public void testCreate() throws Exception {
        EmployeeDto createdEmployee = EmployeeDto.builder().id(4L).lastName("created")
                .firstName("created")
                .middleName("created")
                .sortNumber("created")
                .phone("created")
                .inn("012341234234")
                .description("created")
                .email("created")
                .password("created").build();
        String createdEmployeeJson = new Gson().toJson(createdEmployee);
        mockMvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON)
                        .content(createdEmployeeJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdEmployeeJson));
        mockMvc.perform(get("/api/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
    @Test
    public void testUpdate() throws Exception {
        EmployeeDto updatedEmployee = EmployeeDto.builder().id(3L).lastName("updated")
                .firstName("updated")
                .middleName("updated")
                .sortNumber("updated")
                .phone("updated")
                .inn("012345678903")
                .description("updated")
                .email("updated")
                .password("updated").build();
        String updatedEmployeeJson = new Gson().toJson(updatedEmployee);
        mockMvc.perform(put("/api/employee").contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedEmployeeJson));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/employee/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
