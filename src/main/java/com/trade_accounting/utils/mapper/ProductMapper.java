package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    default Product toModel(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .volume(productDto.getVolume())
                .weight(productDto.getWeight())
                .saleTax(productDto.getSaleTax())
                .countryOrigin(productDto.getCountryOrigin())
                .description(productDto.getDescription())
                .archive(productDto.getArchive())
                .service(productDto.getService())
                .purchasePrice(productDto.getPurchasePrice())
                .build();
    }

    default ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        if (product == null) {
            return null;
        } else {
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setVolume(product.getVolume());
            productDto.setWeight(product.getWeight());
            productDto.setSaleTax(product.getSaleTax());
            productDto.setCountryOrigin(product.getCountryOrigin());
            productDto.setDescription(product.getDescription());
            productDto.setArchive(product.getArchive());
            productDto.setService(product.getService());
            productDto.setPurchasePrice(product.getPurchasePrice());
            return productDto;
        }
    }

    List<ProductDto> toListDto(Collection<Product> products);

}