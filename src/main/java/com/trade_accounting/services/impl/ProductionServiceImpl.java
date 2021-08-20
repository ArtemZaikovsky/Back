package com.trade_accounting.services.impl;

import com.trade_accounting.models.Production;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.models.dto.ProductionDto;
import com.trade_accounting.repositories.ProductionRepository;
import com.trade_accounting.repositories.RequestsProductionsRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.services.interfaces.ProductionService;
import com.trade_accounting.utils.mapper.ProductionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final TechnicalCardRepository technicalCardRepository;
    private final RequestsProductionsRepository requestsProductionsRepository;

    private final ProductionMapper productionMapper;

    @Override
    public List<ProductionDto> getAll() {
        final List<ProductionDto> collect = productionRepository.findAll().stream()
                .map(productionMapper::toDto)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public ProductionDto getById(Long id) {
        return productionMapper.toDto(productionRepository.getOne(id));
    }

    @Override
    public ProductionDto create(ProductionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public ProductionDto update(ProductionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        productionRepository.deleteById(id);
    }

    private ProductionDto saveOrUpdate(ProductionDto dto) {
        Production production = productionMapper.toModel(dto);
        production.setTechnicalCard(technicalCardRepository.getOne(dto.getTechnicalCardId()));
        production.setRequestsProductions(requestsProductionsRepository.getOne(dto.getRequestsProductionsId()));
        return productionMapper.toDto(productionRepository.save(production));
    }
}
