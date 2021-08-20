package com.trade_accounting.services.impl;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.dto.AcceptanceDto;
import com.trade_accounting.repositories.AcceptanceRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.dto.AcceptanceDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.AcceptanceModelStubs;
import com.trade_accounting.utils.mapper.AcceptanceMapperImpl;
import org.junit.jupiter.api.DisplayName;
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
@DisplayName("Тест сервиса Acceptance вкладки Приёмка")
public class AcceptanceServiceImplTest {

    @Mock
    AcceptanceRepository acceptanceRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    ContractRepository contractRepository;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Spy
    AcceptanceMapperImpl acceptanceMapper;

    @InjectMocks
    AcceptanceServiceImpl acceptanceService;

    @Test
    public void getAll() {
        when(acceptanceRepository.findAll()).thenReturn(
                List.of(AcceptanceModelStubs.getAcceptance(1L),
                        AcceptanceModelStubs.getAcceptance(2L),
                        AcceptanceModelStubs.getAcceptance(3L))
        );

        List<AcceptanceDto> acceptanceDtos = acceptanceService.getAll();

        assertEquals(3, acceptanceDtos.size());
    }

    @Test
    public void getById() {
        when(acceptanceRepository.getOne(anyLong())).thenReturn(AcceptanceModelStubs.getAcceptance(1L));

        AcceptanceDto acceptanceDto = acceptanceService.getById(1L);

        assertEquals(1, acceptanceDto.getId());
    }

    @Test
    public void create() {
        saveOrUpdate();
    }

    @Test
    public void update() {
        saveOrUpdate();
    }

    @Test
    public void deleteById() {
        acceptanceService.deleteById(anyLong());
        verify(acceptanceRepository).deleteById(anyLong());
    }


    private void saveOrUpdate() {
        when(acceptanceRepository.save(any(Acceptance.class))).thenReturn(AcceptanceModelStubs.getAcceptance(1L));
        AcceptanceDto acceptanceDto = acceptanceService.create(AcceptanceDtoStubs.getAcceptanceDto(1L));
        assertEquals(1, acceptanceDto.getId());
        verify(acceptanceRepository).save(any(Acceptance.class));
    }
}
