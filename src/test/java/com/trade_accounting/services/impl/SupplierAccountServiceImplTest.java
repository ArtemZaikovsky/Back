package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.SupplierAccountRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.SupplierAccountDtoStubs;
import com.trade_accounting.utils.mapper.SupplierAccountMapperImpl;
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
class SupplierAccountServiceImplTest {

    @Spy
    private SupplierAccountMapperImpl supplierAccountMapper;

    @Mock
    private SupplierAccountRepository repository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    ContractRepository contractRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @InjectMocks
    private SupplierAccountServiceImpl service;

    @Test
    void getRepositoryFilledTest() {
        assertNotNull(repository, "Repository is not filled");
    }

    @Test
    void getAll_shouldReturnListFilledSupplierAccountDto() {
        when(repository.getAll())
                .thenReturn(
                        List.of(SupplierAccountDtoStubs.getSupplierAccountDto(1L),
                                SupplierAccountDtoStubs.getSupplierAccountDto(2L),
                                SupplierAccountDtoStubs.getSupplierAccountDto(3L)));
        List<SupplierAccountDto> list = service.getAll();
        assertNotNull(list, "list is not filled");
        assertEquals(3, list.size());
    }

    @Test
    void getById_shouldReturnFilledSupplierAccount() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(ModelStubs.getSupplierAccount(anyLong())));
        SupplierAccountDto dto = service.getById(1L);
        assertNotNull(dto, "dto is not filled");
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
        service.deleteById(anyLong());
        verify(repository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(repository.save(any())).thenReturn(ModelStubs.getSupplierAccount(1L));
        SupplierAccountDto dto = service.create(SupplierAccountDtoStubs.getSupplierAccountDto(1L));
        assertNotNull(dto);
        assertEquals(1, dto.getId());
        verify(repository).save(any());
    }
}