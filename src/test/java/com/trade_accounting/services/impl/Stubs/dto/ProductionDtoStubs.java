package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ProductionDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.ProductionMapper;
import org.mapstruct.factory.Mappers;

public class ProductionDtoStubs {
    private static final ProductionMapper mapper = Mappers.getMapper(ProductionMapper.class);
    public static ProductionDto getProductionDto(Long id) {
        return mapper.toDto(ModelStubs.getProduction(id));
    }
}
