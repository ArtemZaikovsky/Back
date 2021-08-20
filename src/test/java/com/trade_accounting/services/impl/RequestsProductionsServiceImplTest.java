package com.trade_accounting.services.impl;

import com.trade_accounting.models.RequestsProductions;
import com.trade_accounting.models.dto.RequestsProductionsDto;
import com.trade_accounting.repositories.RequestsProductionsRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.dto.RequestsProductionsDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.RequestsProductionsModelStubs;
import com.trade_accounting.utils.mapper.RequestsProductionsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestsProductionsServiceImplTest {

    @Mock
    TechnicalCardRepository technicalCardRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Mock
    RequestsProductionsRepository requestsProductionsRepository;

    @Spy
    RequestsProductionsMapper requestsProductionsMapper;

    @InjectMocks
    RequestsProductionsServiceImpl requestsProductionsService;


    @Test
    void getAll() {
        when(requestsProductionsRepository.findAll())
                .thenReturn(
                        List.of(
                                RequestsProductionsModelStubs.getRequestsProductions(1L),
                                RequestsProductionsModelStubs.getRequestsProductions(2L),
                                RequestsProductionsModelStubs.getRequestsProductions(3L)
                        ));

        List<RequestsProductionsDto> requestsProductionsDtos = requestsProductionsService.getAll();

        assertEquals(3, requestsProductionsDtos.size());
    }

    @Test
    void getById() {
        when(requestsProductionsRepository.getOne(anyLong()))
                .thenReturn(
                        RequestsProductionsModelStubs.getRequestsProductions(1L)
                );

        RequestsProductionsDto requestsProductionsDto = requestsProductionsService.getById(1L);
        assertEquals(1, requestsProductionsDto.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        requestsProductionsService.deleteById(anyLong());
        verify(requestsProductionsRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(requestsProductionsRepository.save(any(RequestsProductions.class)))
                .thenReturn(RequestsProductionsModelStubs.getRequestsProductions(1L));

        RequestsProductionsDto requestsProductionsDto = requestsProductionsService
                .create(RequestsProductionsDtoStubs.getDto(1L));

        assertEquals(1, requestsProductionsDto.getId());
        verify(requestsProductionsRepository).save(any(RequestsProductions.class));
    }
}