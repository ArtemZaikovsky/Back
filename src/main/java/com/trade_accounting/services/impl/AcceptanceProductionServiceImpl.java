package com.trade_accounting.services.impl;

import com.trade_accounting.models.AcceptanceProduction;
import com.trade_accounting.models.dto.AcceptanceProductionDto;
import com.trade_accounting.repositories.AcceptanceProductionRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.AcceptanceProductionService;
import com.trade_accounting.utils.mapper.AcceptanceProductionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptanceProductionServiceImpl implements AcceptanceProductionService {

    private final AcceptanceProductionRepository acceptanceProductionRepository;

    private final ProductRepository productRepository;

    private final AcceptanceProductionMapper acceptanceProductionMapper;

    public AcceptanceProductionServiceImpl(AcceptanceProductionRepository acceptanceProductionRepository,
                                           ProductRepository productRepository,
                                           AcceptanceProductionMapper acceptanceProductionMapper) {
        this.acceptanceProductionRepository = acceptanceProductionRepository;
        this.productRepository = productRepository;
        this.acceptanceProductionMapper = acceptanceProductionMapper;
    }

    @Override
    public List<AcceptanceProductionDto> getAll() {
        return acceptanceProductionRepository.findAll().stream()
                .map(acceptanceProductionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AcceptanceProductionDto getById(Long id) {
        return acceptanceProductionMapper.toDto(
                acceptanceProductionRepository.getOne(id)
        );
    }

    @Override
    public AcceptanceProductionDto create(AcceptanceProductionDto dto) {
        AcceptanceProduction acceptanceProduction = acceptanceProductionMapper.toModel(dto);
        acceptanceProduction.setProduct(productRepository.getOne(dto.getProductId()));
        return acceptanceProductionMapper.toDto(acceptanceProductionRepository
                .save(acceptanceProduction));
    }

    @Override
    public AcceptanceProductionDto update(AcceptanceProductionDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        acceptanceProductionRepository.deleteById(id);
    }
}
