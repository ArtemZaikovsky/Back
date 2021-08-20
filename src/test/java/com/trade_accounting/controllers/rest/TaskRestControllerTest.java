package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/task-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")
@WithMockUser

public class TaskRestControllerTest {

    @Autowired
    private TaskRestController taskRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception{
        assertNotNull(taskRestController, "Task Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetById() throws Exception {
        String taskJson = new Gson().toJson(TaskDto.builder().id(3L).description("desc3")
                .completed(true)
                .taskAuthorId(3L)
                .build());
        mockMvc.perform(get("/api/tasks/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(taskJson));
    }

    @Test
    public void testCreate() throws Exception {
        TaskDto createdTask = TaskDto.builder().id(4L).description("desc4")
                .creationDateTime("2015-04-10 03:09:02")
                .deadlineDateTime("2015-04-11 03:09:02")
                .completed(true)
                .employeeId(1L)
                .taskAuthorId(1L)
                .build();
        String createdTaskJson = new Gson().toJson(createdTask);
        mockMvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(createdTaskJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        TaskDto updatedTask = TaskDto.builder().id(4L).description("desc4")
                .creationDateTime("2016-04-10 03:09:02")
                .deadlineDateTime("2016-04-11 03:09:02")
                .completed(true)
                .employeeId(4L)
                .taskAuthorId(4L)
                .build();
        String updatedTaskJson = new Gson().toJson(updatedTask);
        mockMvc.perform(put("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(updatedTaskJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/tasks/3"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}