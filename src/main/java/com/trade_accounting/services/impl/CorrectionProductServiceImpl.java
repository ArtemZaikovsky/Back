package com.trade_accounting.services.impl;

import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.CorrectionProductDto;
import com.trade_accounting.repositories.CorrectionProductRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.CorrectionProductService;
import com.trade_accounting.utils.mapper.CorrectionProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CorrectionProductServiceImpl implements CorrectionProductService {

    private final CorrectionProductRepository correctionProductRepository;
    private final ProductRepository productRepository;
    private final CorrectionProductMapper correctionProductMapper;

    @Override
    public List<CorrectionProductDto> getAll() {
        return correctionProductRepository.findAll().stream()
                .map(correctionProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CorrectionProductDto getById(Long id) {
        Optional<CorrectionProduct> correctionProduct = correctionProductRepository.findById(id);
        return correctionProductMapper.toDto(correctionProduct.orElse(new CorrectionProduct()));
    }

    @Override
    public CorrectionProductDto create(CorrectionProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public CorrectionProductDto update(CorrectionProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        correctionProductRepository.deleteById(id);
    }

    private CorrectionProductDto saveOrUpdate(CorrectionProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());
        CorrectionProduct correctionProduct = correctionProductMapper.toModel(dto);
        correctionProduct.setProduct(product.orElse(null));

        return correctionProductMapper.toDto(correctionProductRepository.save(correctionProduct));
    }
}
