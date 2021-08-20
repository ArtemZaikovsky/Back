package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.TaxSystemMapper;
import org.mapstruct.factory.Mappers;

public class TaxSystemDtoStubs {
    private static final TaxSystemMapper mapper = Mappers.getMapper(TaxSystemMapper.class);

    public static TaxSystemDto getTaxSystemDto(Long id) {
        return mapper.toDto(
                ModelStubs.getTaxSystem(id)
        );
    }
}
