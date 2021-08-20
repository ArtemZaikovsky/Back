package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.AttributeOfCalculationObject;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeOfCalculationObjectMapper {
    //AttributeOfCalculationObjectDto
    AttributeOfCalculationObjectDto toDto(AttributeOfCalculationObject attributeOfCalculationObject);

    AttributeOfCalculationObject toModel(AttributeOfCalculationObjectDto attributeOfCalculationObjectDto);
}
