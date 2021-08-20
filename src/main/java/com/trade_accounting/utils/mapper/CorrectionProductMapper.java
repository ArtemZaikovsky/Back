package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.dto.CorrectionProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CorrectionProductMapper {
    //    CorrectionProduct
    @Mappings({
            @Mapping(source = "product.id", target = "productId")
    })
    CorrectionProductDto toDto(CorrectionProduct correction);

    CorrectionProduct toModel(CorrectionProductDto correctionDto);
}
