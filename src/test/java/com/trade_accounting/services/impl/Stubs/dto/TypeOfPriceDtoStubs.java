package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.TypeOfPriceMapper;
import org.mapstruct.factory.Mappers;

public class TypeOfPriceDtoStubs {
    private static final TypeOfPriceMapper mapper = Mappers.getMapper(TypeOfPriceMapper.class);

    public static TypeOfPriceDto getTypeOfPriceDto(Long id) {
        return mapper.toDto(ModelStubs.getTypeOfPrice(id));
    }
}
