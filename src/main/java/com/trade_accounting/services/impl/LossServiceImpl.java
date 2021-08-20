package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Loss;
import com.trade_accounting.models.LossProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.LossDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LossProductRepository;
import com.trade_accounting.repositories.LossRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.LossService;
import com.trade_accounting.utils.mapper.LossMapper;
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
public class LossServiceImpl implements LossService {
    private final LossRepository lossRepository;
    private final LossProductRepository lossProductRepository;
    private final CompanyRepository companyRepository;
    private final WarehouseRepository warehouseRepository;
    private final LossMapper lossMapper;

    @Override
    public List<LossDto> getAll() {
        return lossRepository.findAll().stream()
                .map(lossMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LossDto getById(Long id) {
        return lossMapper.toDto(lossRepository.getOne(id));
    }

    @Override
    public LossDto create(LossDto dto) {
        return saveOrUpdate(dto);
    }



    @Override
    public LossDto update(LossDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        lossRepository.deleteById(id);
    }

    private LossDto saveOrUpdate(LossDto dto) {
        Loss loss = lossMapper.toModel(dto);

        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        Warehouse warehouse = warehouseRepository.getOne(dto.getWarehouseId());
        LocalDateTime date = LocalDateTime.parse(dto.getDate().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        List<LossProduct> lossProducts = dto.getLossProductsIds().stream()
                .map(id -> lossProductRepository.findById(id).orElse(null))
                .collect(Collectors.toList());

        loss.setCompany(company);
        loss.setWarehouse(warehouse);
        loss.setDate(date);
        loss.setLossProducts(lossProducts);

        return lossMapper.toDto(lossRepository.save(loss));
    }
}
