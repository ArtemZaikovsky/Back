package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Inventarization;
import com.trade_accounting.models.InventarizationProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.InventarizationDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.InventarizationProductRepository;
import com.trade_accounting.repositories.InventarizationRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.InventarizationService;
import com.trade_accounting.utils.mapper.InventarizationMapper;
import com.trade_accounting.utils.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InventarizationServiceImpl implements InventarizationService {

    private final InventarizationRepository inventarizationRepository;
    private final InventarizationProductRepository inventarizationProductRepository;
    private final WarehouseRepository warehouseRepository;
    private final CompanyRepository companyRepository;
    private final WarehouseMapper warehouseMapper;
    private final InventarizationMapper inventarizationMapper;

    @Override
    public List<InventarizationDto> getAll() {
        List<InventarizationDto> listInventarizationDto = inventarizationRepository.findAll()
                .stream()
                .map(inventarizationMapper::toDto)
                .collect(Collectors.toList());
        return listInventarizationDto;
    }

    @Override
    public InventarizationDto getById(Long id) {
        Optional<Inventarization> inventarization = inventarizationRepository.findById(id);
        return inventarizationMapper.toDto(inventarization.orElse(new Inventarization()));
    }

    @Override
    public InventarizationDto create(InventarizationDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public InventarizationDto update(InventarizationDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        inventarizationRepository.deleteById(id);
    }

    private InventarizationDto saveOrUpdate(InventarizationDto dto) {
        Inventarization inventarization = inventarizationMapper.toModel(dto);
        Warehouse warehouse = warehouseMapper.toModel(warehouseRepository.getById(dto.getWarehouseId()));
        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        LocalDateTime date = LocalDateTime.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        List<InventarizationProduct> inventarizationProducts = dto.getInventarizationProductIds()
                .stream()
                .map(id -> inventarizationProductRepository.findById(id).orElse(null))
                .collect(Collectors.toList());

        inventarization.setWarehouse(warehouse);
        inventarization.setCompany(company);
        inventarization.setDate(date);
        inventarization.setInventarizationProducts(inventarizationProducts);

        return inventarizationMapper.toDto(inventarizationRepository.save(inventarization));
    }
}
