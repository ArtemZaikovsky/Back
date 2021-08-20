package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.services.impl.Stubs.model.PriceListModelStubs;
import com.trade_accounting.utils.mapper.PriceListMapper;
import org.mapstruct.factory.Mappers;

public class PriceListDtoStubs {

    private static final PriceListMapper mapper = Mappers.getMapper(PriceListMapper.class);

    public static PriceListDto getDto(Long id){
        return mapper.toDto(PriceListModelStubs.getPriceList(id));
    }
}
