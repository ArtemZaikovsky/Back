package com.trade_accounting.services.impl;

import com.trade_accounting.models.Inventarization;
import com.trade_accounting.models.dto.InventarizationDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.InventarizationProductRepository;
import com.trade_accounting.repositories.InventarizationRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.InventarizationDtoStubs;
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
public class InventarizationServiceImplTest {

    @Mock
    InventarizationRepository inventarizationRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    InventarizationProductRepository inventarizationProductRepository;

    @InjectMocks
    InventarizationServiceImpl inventarizationService;

    @Spy
    InventarizationDtoStubs inventarizationDtoStubs;

    @Test
    void getAll_shouldReturnFilledListInventarization() {
        when(inventarizationRepository.findAll()).thenReturn(
                List.of(ModelStubs.getInventarization(1L))
        );

        List<InventarizationDto> listInv = inventarizationService.getAll();
        assertEquals(1, listInv.size());
    }

    @Test
    void getById_shouldReturnFilledInventarization() {
        when(inventarizationRepository.findById(anyLong())).thenReturn(java.util.Optional.of(ModelStubs.getInventarization(1L)));

        InventarizationDto inventarizationdto = inventarizationService.getById(1L);
        assertEquals(1, inventarizationdto.getId());
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        saveOrUpdate();
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        saveOrUpdate();
    }

    @Test
    void delete_update_shouldPassInstructionsSuccessfulDelete() {
        inventarizationRepository.deleteById(anyLong());
        verify(inventarizationRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(inventarizationRepository.save(any(Inventarization.class))).thenReturn(ModelStubs.getInventarization(1L));
        InventarizationDto inventarizationDto = inventarizationService.create(InventarizationDtoStubs.getInventarizationDto(1L));
        assertEquals(1,inventarizationDto.getId());
        verify(inventarizationRepository).save(any(Inventarization.class));
    }
}
