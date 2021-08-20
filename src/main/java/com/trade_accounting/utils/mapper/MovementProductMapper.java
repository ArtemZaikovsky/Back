package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.MovementProduct;
import com.trade_accounting.models.dto.MovementProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementProductMapper {
    //    MovementProduct

    @Mapping(source = "product.id", target = "productId")
    MovementProductDto toDto(MovementProduct movement);

    MovementProduct toModel(MovementProductDto movementDto);
}
