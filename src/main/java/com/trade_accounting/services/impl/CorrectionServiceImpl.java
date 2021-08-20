package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Correction;
import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.CorrectionDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.CorrectionProductRepository;
import com.trade_accounting.repositories.CorrectionRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.CorrectionService;
import com.trade_accounting.utils.mapper.CorrectionMapper;
import com.trade_accounting.utils.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CorrectionServiceImpl implements CorrectionService {

    private final CorrectionRepository correctionRepository;
    private final WarehouseRepository warehouseRepository;
    private final CompanyRepository companyRepository;
    private final CorrectionProductRepository correctionProductRepository;
    private final WarehouseMapper warehouseMapper;
    private final CorrectionMapper correctionMapper;

    @Override
    public List<CorrectionDto> getAll() {
        return correctionRepository.getAll().stream()
                .map(correctionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CorrectionDto getById(Long id) {
        return correctionMapper.toDto(correctionRepository.getCorrectionById(id));
    }

    @Override
    public CorrectionDto create(CorrectionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public CorrectionDto update(CorrectionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        correctionRepository.deleteById(id);
    }

    private CorrectionDto saveOrUpdate(CorrectionDto dto) {
        Correction correction = correctionMapper.toModel(dto);
        Warehouse warehouse = warehouseMapper.toModel(warehouseRepository.getById(dto.getWarehouseId()));
        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        LocalDateTime date = LocalDateTime.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        List<CorrectionProduct> correctionProducts = dto.getCorrectionProductIds().stream()
                .map(id -> correctionProductRepository.findById(id).orElse(null)).collect(Collectors.toList());

        correction.setWarehouse(warehouse);
        correction.setCompany(company);
        correction.setDate(date);
        correction.setCorrectionProducts(correctionProducts);

        return correctionMapper.toDto(correctionRepository.save(correction));
    }
}

