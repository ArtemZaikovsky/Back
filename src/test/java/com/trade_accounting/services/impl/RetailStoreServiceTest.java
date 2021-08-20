package com.trade_accounting.services.impl;

import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.impl.Stubs.dto.RetailStoreDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.RetailStoreMapper;
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
public class RetailStoreServiceTest {

    @Mock
    RetailStoreRepository retailStoreRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    RetailStoreServiceImpl retailStoreService;

    @Spy
    RetailStoreMapper retailStoreMapper;

    @Test
    void getAll_shouldReturnFilledListRetailStore() {
        when(retailStoreRepository.findAll()).thenReturn(
                List.of(RetailStoreModelStubs.getRetailStore(1L))
        );

        List<RetailStoreDto> listInv = retailStoreService.getAll();
        assertEquals(1, listInv.size());
    }

    @Test
    void getById_shouldReturnFilledRetailStore() {
        when(retailStoreRepository.findById(anyLong())).thenReturn(java.util.Optional.of(RetailStoreModelStubs.getRetailStore(1L)));

        RetailStoreDto retailStoreDto = retailStoreService.getById(1L);
        assertEquals(1, retailStoreDto.getId());
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
        retailStoreRepository.deleteById(anyLong());
        verify(retailStoreRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailStoreRepository.save(any(RetailStore.class))).thenReturn(RetailStoreModelStubs.getRetailStore(1L));
        RetailStoreDto retailStoreDto = retailStoreService.create(RetailStoreDtoStubs.getDto(1L));
        assertEquals(1,retailStoreDto.getId());
        verify(retailStoreRepository).save(any(RetailStore.class));
    }
}
