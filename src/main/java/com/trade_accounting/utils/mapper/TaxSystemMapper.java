package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TaxSystem;
import com.trade_accounting.models.dto.TaxSystemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxSystemMapper {
    //TaxSystem
    TaxSystemDto toDto(TaxSystem taxSystem);

    TaxSystem toModel(TaxSystemDto taxSystemDto);
}
