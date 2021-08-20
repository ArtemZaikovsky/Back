package com.trade_accounting.services.impl;

import com.trade_accounting.models.InventarizationProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.InventarizationProductDto;
import com.trade_accounting.repositories.InventarizationProductRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.InventarizationProductService;
import com.trade_accounting.utils.mapper.InventarizationProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InventarizationProductServiceImpl implements InventarizationProductService {

    private final InventarizationProductRepository inventarizationProductRepository;
    private final InventarizationProductMapper inventarizationProductMapper;
    private final ProductRepository productRepository;

    @Override
    public List<InventarizationProductDto> getAll() {

        List<InventarizationProductDto> inventarizationProductDtos = inventarizationProductRepository.findAll()
                .stream()
                .map(inventarizationProductMapper::toDto)
                .collect(Collectors.toList());

        return inventarizationProductDtos;
    }

    @Override
    public InventarizationProductDto getById(Long id) {
        Optional<InventarizationProduct> inventarizationProduct = inventarizationProductRepository.findById(id);
        return inventarizationProductMapper.toDto(inventarizationProduct.orElse(new InventarizationProduct()));
    }

    @Override
    public InventarizationProductDto create(InventarizationProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public InventarizationProductDto update(InventarizationProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        inventarizationProductRepository.deleteById(id);
    }

    public InventarizationProductDto saveOrUpdate(InventarizationProductDto dto) {
        InventarizationProduct inventarizationProduct = inventarizationProductMapper.toModel(dto);
        Optional<Product> product = productRepository.findById(dto.getProductId());
        inventarizationProduct.setProduct(product.orElse(null));

        return inventarizationProductMapper.toDto(inventarizationProductRepository.save(inventarizationProduct));
    }
}
