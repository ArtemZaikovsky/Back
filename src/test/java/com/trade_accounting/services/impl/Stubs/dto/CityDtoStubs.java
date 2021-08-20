package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.fias.CityDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.CityMapper;
import org.mapstruct.factory.Mappers;

public class CityDtoStubs {
    private static final CityMapper mapper = Mappers.getMapper(CityMapper.class);

    public static CityDto getCityDto(Long id) {
        return mapper.toDto(ModelStubs.getCity(id));
    }
}
