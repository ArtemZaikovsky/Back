package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.fias.StreetDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.StreetMapper;
import org.mapstruct.factory.Mappers;

public class StreetDtoStubs {
    private static final StreetMapper mapper = Mappers.getMapper(StreetMapper.class);

    public static StreetDto getStreetDto(Long id) {
        return mapper.toDto(ModelStubs.getStreet(id));
    }
}
