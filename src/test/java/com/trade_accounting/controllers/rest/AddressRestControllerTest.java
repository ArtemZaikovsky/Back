package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.AddressDto;
import com.trade_accounting.services.interfaces.AddressService;
import com.trade_accounting.services.interfaces.CheckEntityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/Address-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")

class AddressRestControllerTest {
    @Autowired
    private AddressService addressService;
    @Autowired
    private CheckEntityService checkEntityService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getById() throws Exception {
        String addressDtoJson = new Gson().toJson(AddressDto.builder()
                .id(1L)
                .index("Index 1")
                .country("Country 1")
                .region("Region 1")
                .city("City 1")
                .street("Street 1")
                .house("House 1")
                .apartment("Apartment 1")
                .build()
        );

        mockMvc.perform(get("/api/address/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(addressDtoJson));
    }

    @Test
    void create() throws Exception {
        String addressDtoJson = new Gson().toJson(AddressDto.builder()
                .id(6L)
                .index("Index 6")
                .country("Country 6")
                .region("Region 6")
                .city("City 6")
                .street("Street 6")
                .house("House 6")
                .apartment("Apartment 6")
                .build()
        );

        mockMvc.perform(post("/api/address")
                .contentType(MediaType.APPLICATION_JSON).content(addressDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(addressDtoJson));

        mockMvc.perform(get("/api/address/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(addressDtoJson));
    }

    @Test
    void update() throws Exception {
        String addressDtoJson = new Gson().toJson(AddressDto.builder()
                .id(2L)
                .index("Index 2 _UPDATED_")
                .country("Country 2 _UPDATED_")
                .region("Region 2 _UPDATED_")
                .city("City 2 _UPDATED_")
                .street("Street 2 _UPDATED_")
                .house("House 2 _UPDATED_")
                .apartment("Apartment 2 _UPDATED_")
                .build()
        );

        mockMvc.perform(put("/api/address")
                .contentType(MediaType.APPLICATION_JSON).content(addressDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(addressDtoJson));

        mockMvc.perform(get("/api/address/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(addressDtoJson));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(get("/api/address/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        mockMvc.perform(delete("/api/address/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        mockMvc.perform(get("/api/address/5"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(authenticated());
    }
}