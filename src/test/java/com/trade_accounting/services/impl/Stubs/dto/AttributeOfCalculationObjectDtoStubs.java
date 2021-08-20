package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.AttributeOfCalculationObjectMapper;
import org.mapstruct.factory.Mappers;

public class AttributeOfCalculationObjectDtoStubs {
    private static final AttributeOfCalculationObjectMapper mapper = Mappers.getMapper(AttributeOfCalculationObjectMapper.class);
    public static AttributeOfCalculationObjectDto getAttributeOfCalculationObjectDto(Long id) {
        return mapper.toDto(
                ModelStubs.getAttributeOfCalculationObject(id)
        );
    }
}
