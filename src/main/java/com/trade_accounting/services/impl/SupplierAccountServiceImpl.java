package com.trade_accounting.services.impl;

import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.SupplierAccountRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.SupplierAccountService;
import com.trade_accounting.utils.mapper.SupplierAccountMapper;
import com.trade_accounting.utils.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierAccountServiceImpl implements SupplierAccountService {

    private final SupplierAccountRepository supplierAccountRepository;
    private final SupplierAccountMapper supplierAccountMapper;
    private final WarehouseMapper warehouseMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public List<SupplierAccountDto> getAll() {
        return supplierAccountRepository.getAll();
    }

    @Override
    public SupplierAccountDto getById(Long id) {
        Optional<SupplierAccount> invoicesToCustomers = supplierAccountRepository.findById(id);
        return supplierAccountMapper.toDto(invoicesToCustomers.orElse(new SupplierAccount()));
    }

    @Override
    public SupplierAccountDto create(SupplierAccountDto createSupplier) {
        SupplierAccount saveInvoices = SupplierAccount.builder().id(createSupplier.getId()).date(createSupplier.getDate())
                .comment(createSupplier.getComment()).isSpend(createSupplier.getIsSpend())
                .company(companyRepository.getCompaniesById(createSupplier.getCompanyId()))
                .warehouse(warehouseMapper.toModel(warehouseRepository.getById(createSupplier.getWarehouseId())))
                .contract(contractRepository.getById(createSupplier.getContractId()))
                .contractor((contractorRepository.getOne(createSupplier.getContractorId())))
                .build();
        return supplierAccountMapper.toDto(supplierAccountRepository.save(saveInvoices));
    }

    @Override
    public SupplierAccountDto update(SupplierAccountDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        supplierAccountRepository.deleteById(id);
    }


    @Override
    public List<SupplierAccountDto> searchByString(String nameFilter) {
        if(nameFilter.matches("[0-9]+")) {
            List<SupplierAccountDto> searchForNumber = supplierAccountRepository.searchById(Long.parseLong(nameFilter));
            return searchForNumber;
        } else if ("null".equals(nameFilter) || nameFilter.isEmpty()) {
            List<SupplierAccountDto> supplierAccountList = supplierAccountRepository.getAll();
            return supplierAccountList;
        } else {
            List<SupplierAccountDto> supplierAccountListDto = supplierAccountRepository.searchByNameFilter(nameFilter);
            return supplierAccountListDto;
        }
    }

    @Override
    public List<SupplierAccountDto> search(Specification<SupplierAccount> spec) {
        return executeSearch(supplierAccountRepository, supplierAccountMapper::toDto, spec);
    }
}
