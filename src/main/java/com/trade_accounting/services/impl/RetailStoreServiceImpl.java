package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.interfaces.RetailStoreService;
import com.trade_accounting.utils.mapper.RetailStoreMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RetailStoreServiceImpl implements RetailStoreService {

    private final RetailStoreRepository retailStoreRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final RetailStoreMapper retailStoreMapper;

    public RetailStoreServiceImpl(RetailStoreRepository retailStoreRepository,
                                  CompanyRepository companyRepository,
                                  EmployeeRepository employeeRepository, RetailStoreMapper retailStoreMapper) {
        this.retailStoreRepository = retailStoreRepository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.retailStoreMapper = retailStoreMapper;
    }

    @Override
    public List<RetailStoreDto> getAll() {
        return retailStoreRepository.findAll().stream()
                .map(retailStoreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailStoreDto getById(Long id) {
        Optional<RetailStore> retailStore = retailStoreRepository.findById(id);
        return retailStoreMapper.toDto(
                retailStore.orElse(new RetailStore()));
    }

    @Override
    public RetailStoreDto create(RetailStoreDto retailStoreDto) {
        RetailStore retailStore = retailStoreMapper.toModel(retailStoreDto);
        Company company = companyRepository.getCompaniesById(retailStoreDto.getCompanyId());

        List<Employee> cashiers = retailStoreDto.getCashiersIds().stream()
                .map(id -> employeeRepository.findById(id).orElse(null)).collect(Collectors.toList());

        retailStore.setCashiers(cashiers);
        retailStore.setCompany(company);

        return retailStoreMapper.toDto(retailStoreRepository.save(retailStore));
    }

    @Override
    public RetailStoreDto update(RetailStoreDto retailStoreDto) {
        create(retailStoreDto);
        return retailStoreDto;
    }

    @Override
    public void deleteById(Long id) {
        retailStoreRepository.deleteById(id);
    }
}
