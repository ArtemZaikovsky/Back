package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.CurrencyMapper;
import org.mapstruct.factory.Mappers;

public class CurrencyDtoStubs {
    private static final CurrencyMapper mapper = Mappers.getMapper(CurrencyMapper.class);
    public static CurrencyDto getCurrencyDto(Long id) {
        return mapper.toDto(
                ModelStubs.getCurrency(id)
        );
    }
}
