package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeOfPriceMapper {
    //TypeOfPrice
    TypeOfPriceDto toDto(TypeOfPrice typeOfPrice);

    TypeOfPrice toModel(TypeOfPriceDto typeOfPriceDto);
}
