package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.CompanyDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
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

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
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
@Sql(value = "/company-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")
class CompanyRestControllerTest {

    @Autowired
    private CompanyRestController companyRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
     void testExistence() throws Exception {
        assertNotNull(companyRestController, "CompanyRestController is null");
    }

    @SneakyThrows
    @Test
     void getAllTest() {
        mockMvc.perform(get("/api/company"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @SneakyThrows
    @Test
     void testGetAll() {
        mockMvc.perform(get("/api/company/search/?leaderManagerPosition=testLeaderMeneger"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].leader", containsInAnyOrder("testLeader", "Leader")));
    }

    @SneakyThrows
    @Test
     void getById() {
        mockMvc.perform(get("/api/company/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fax").value("810-41-1234567824"))
                .andExpect(jsonPath("$.inn").value("4321"))
                .andExpect(jsonPath("$.email").value("karimogon@mail.ru"));
    }

    @SneakyThrows
    @Test
     void getByEmail() {
        mockMvc.perform(get("/api/company/veraogon@mail.ru"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.stamp").value("stampTest"));
    }

    @SneakyThrows
    @Test
     void create() {
        CompanyDto companyDto = CompanyDto.builder()
                .id(3L)
                .name("name")
                .inn("0000")
                .sortNumber("0003")
                .phone("+74657261886")
                .fax("810-41-1234567823")
                .email("sashaogon@mail.ru")
                .payerVat(true)
                .addressId(1L)
                .commentToAddress("comment")
                .leader("Test")
                .leaderManagerPosition("manager")
                .leaderSignature("signature")
                .chiefAccountant("group")
                .chiefAccountantSignature("groupSig")
                .stamp("stampi")
                .legalDetailDtoId(1L)
                .bankAccountDtoIds(List.of(1L, 2L, 3L))
                .build();

        String companyDtoJson = new Gson().toJson(companyDto);

        mockMvc.perform(post("/api/company")
                .contentType(MediaType.APPLICATION_JSON).content(companyDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(companyDtoJson));

        mockMvc.perform(get("/api/company"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @SneakyThrows
    @Test
     void update() {
        CompanyDto companyDto = CompanyDto.builder()
                .id(2L)
                .name("name")
                .inn("0000")
                .sortNumber("0003")
                .phone("+74657261886")
                .fax("")
                .email("emai@mail.ru")
                .payerVat(true)
                .addressId(2L)
                .commentToAddress("comment1")
                .leader("Test1")
                .leaderManagerPosition("manager1")
                .leaderSignature("signature1")
                .chiefAccountant("group1")
                .chiefAccountantSignature("groupSig1")
                .stamp("stampi1")
                .legalDetailDtoId(2L)
                .bankAccountDtoIds(List.of(3L, 4L, 5L))
                .build();

        String companyDtoJson = new Gson().toJson(companyDto);

        mockMvc.perform(put("/api/company")
                .contentType(MediaType.APPLICATION_JSON).content(companyDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(companyDtoJson));


        mockMvc.perform(get("/api/company"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].email", containsInAnyOrder("veraogon@mail.ru", "emai@mail.ru")))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @SneakyThrows
    @Test
     void deleteById() {
        mockMvc.perform(delete("/api/company/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/company"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));

    }
}