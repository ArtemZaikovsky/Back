package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    //Currency
    CurrencyDto toDto(Currency currency);

    Currency toModel(CurrencyDto currencyDto);
}
