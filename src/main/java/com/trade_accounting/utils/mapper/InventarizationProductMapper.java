package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InventarizationProduct;
import com.trade_accounting.models.dto.InventarizationProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventarizationProductMapper {
    @Mapping(source = "product.id", target = "productId")
    InventarizationProductDto toDto(InventarizationProduct inventarizationProduct);

    InventarizationProduct toModel(InventarizationProductDto inventarizationProductDto);


}
