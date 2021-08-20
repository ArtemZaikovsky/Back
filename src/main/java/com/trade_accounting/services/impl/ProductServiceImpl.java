package com.trade_accounting.services.impl;

import javax.validation.constraints.NotNull;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.PageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.utils.mapper.ImageMapper;
import com.trade_accounting.utils.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.getOne(id);

        ProductDto productDto = productMapper.toDto(product);
        productDto.setImageDtos(imageMapper.toListDto(product.getImages()));

        return productDto;
    }

    @Override
    public ProductDto create(@NotNull ProductDto dto) {
        List<Image> preparedImages = imageMapper.toListModel(dto.getImageDtos(), "product");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        Product product = productMapper.toModel(dto);
        product.setImages(savedImages);
        productRepository.saveAndFlush(product);
        return dto;
    }


    @Override
    public ProductDto update(ProductDto dto) {
        List<Image> preparedImages = imageMapper.toListModel(dto.getImageDtos(), "product");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        Product product = productMapper.toModel(dto);
        product.setImages(savedImages);
        productRepository.saveAndFlush(product);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public List<ProductDto> search(Specification<Product> spec) {
        List<Product> productList = productRepository.findAll(spec);
        return productMapper.toListDto(productList);
    }

    @Override
    public PageDto<ProductDto> search(Specification<Product> specification, Pageable pageParam) {
        Page<Product> page = productRepository.findAll(specification, pageParam);
        return new PageDto<>(
                page.getContent().stream().map(productMapper::toDto).collect(Collectors.toList()),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumberOfElements()
        );
    }

    @Override
    public List<ProductDto> search(String value) {
        return productMapper.toListDto(productRepository.search(value));
    }
}
