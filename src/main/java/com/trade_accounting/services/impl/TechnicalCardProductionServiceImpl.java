package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.dto.TechnicalCardProductionDto;
import com.trade_accounting.repositories.TechnicalCardProductionRepository;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.services.interfaces.TechnicalCardProductionService;
import com.trade_accounting.utils.mapper.ProductMapper;
import com.trade_accounting.utils.mapper.TechnicalCardProductionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicalCardProductionServiceImpl implements TechnicalCardProductionService {
    private final TechnicalCardProductionRepository cardProductionRepository;
    private final TechnicalCardProductionMapper cardProductionMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public List<TechnicalCardProductionDto> getAll() {
        return cardProductionRepository.findAll().stream()
                .map(cardProductionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TechnicalCardProductionDto getById(Long id) {
        return cardProductionMapper.toDto(cardProductionRepository.getOne(id));
    }

    @Override
    public TechnicalCardProductionDto create(TechnicalCardProductionDto dto) {
        TechnicalCardProduction technicalCardProduction = cardProductionMapper.toModel(dto);

        technicalCardProduction.setProduct(
                productMapper.toModel(productService.getById(dto.getProductId()))
        );
        return cardProductionMapper.toDto(cardProductionRepository.save(technicalCardProduction));
    }

    @Override
    public TechnicalCardProductionDto update(TechnicalCardProductionDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        cardProductionRepository.deleteById(id);
    }


    @Override
    public List<TechnicalCardProduction> finaAllById(List<Long> id) {
        return cardProductionRepository.findAllById(id);
    }
}
