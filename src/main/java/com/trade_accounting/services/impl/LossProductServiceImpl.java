package com.trade_accounting.services.impl;


import com.trade_accounting.models.LossProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.LossProductDto;
import com.trade_accounting.repositories.LossProductRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.LossProductService;
import com.trade_accounting.utils.mapper.LossProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LossProductServiceImpl implements LossProductService {
    private final LossProductRepository lossProductRepository;
    private final ProductRepository productRepository;
    private final LossProductMapper lossProductMapper;

    @Override
    public List<LossProductDto> getAll() {
        return lossProductRepository.findAll().stream()
                .map(lossProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LossProductDto getById(Long id) {
        return lossProductMapper.toDto(lossProductRepository.getOne(id));
    }

    @Override
    public LossProductDto create(LossProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public LossProductDto update(LossProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        lossProductRepository.deleteById(id);
    }

    private LossProductDto saveOrUpdate(LossProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());

        LossProduct lossProduct = lossProductMapper.toModel(dto);

        lossProduct.setProduct(product.orElse(null));

        return lossProductMapper.toDto(lossProductRepository.save(lossProduct));
    }
}
