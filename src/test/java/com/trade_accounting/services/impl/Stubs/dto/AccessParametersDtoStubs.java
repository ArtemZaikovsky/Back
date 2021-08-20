package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.AccessParametersDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.AccessParametersMapper;
import org.mapstruct.factory.Mappers;

public class AccessParametersDtoStubs {
    private static final AccessParametersMapper mapper = Mappers.getMapper(AccessParametersMapper.class);
    public static AccessParametersDto getAccessParametersDto(Long id) {
        return mapper.toDto(ModelStubs.getAccessParameters(id));
    }
}
