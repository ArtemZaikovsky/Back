package com.trade_accounting.services.impl;

import com.trade_accounting.models.ReturnToSupplier;
import com.trade_accounting.models.dto.ReturnToSupplierDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.ReturnToSupplierRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.ReturnToSupplierDtoStubs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturnToSupplierServiceImplTest {

    @Mock
    ReturnToSupplierRepository returnToSupplierRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    ContractRepository contractRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Spy
    ReturnToSupplierDtoStubs returnToSupplierDtoStubs;

    @InjectMocks
    ReturnToSupplierServiceImpl returnToSupplierService;

    @Test
    void getRepoFilledNotNull() {
        assertNotNull(returnToSupplierRepository);
    }

    @Test
    void getAll_shouldReturnFilledListReturnToSuppliers() {
        when(returnToSupplierRepository.getAll())
                .thenReturn(
                        List.of(ReturnToSupplierDtoStubs.getReturnToSupplierDto(1L),
                                ReturnToSupplierDtoStubs.getReturnToSupplierDto(2L),
                                ReturnToSupplierDtoStubs.getReturnToSupplierDto(3L)));
        List<ReturnToSupplierDto> list = returnToSupplierService.getAll();
        assertEquals(3, list.size());
    }

    @Test
    void getById_shouldReturnFilledReturnToSupplier() {
        Optional<ReturnToSupplier> model = Optional.of(ModelStubs.getReturnToSupplier(1L));
        when(returnToSupplierRepository.findById(anyLong())).thenReturn(model);
        ReturnToSupplierDto dto = returnToSupplierService.getById(1L);
        assertEquals(1L, dto.getId());
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
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        returnToSupplierService.deleteById(anyLong());
        verify(returnToSupplierRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(returnToSupplierRepository.save(any())).thenReturn(ModelStubs.getReturnToSupplier(1L));
        ReturnToSupplierDto dto = returnToSupplierService.create(ReturnToSupplierDtoStubs.getReturnToSupplierDto(1L));
        assertEquals(1, dto.getId());
        verify(returnToSupplierRepository).save(any(ReturnToSupplier.class));
    }

}
