package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.RemainDto;
import com.trade_accounting.services.impl.Stubs.model.RemainModelStubs;
import com.trade_accounting.utils.mapper.RemainMapper;
import org.mapstruct.factory.Mappers;

public class RemainDtoStubs {
    private static final RemainMapper mapper = Mappers.getMapper(RemainMapper.class);

    public static RemainDto getDto(Long id){
        return mapper.toDto(RemainModelStubs.getRemain(id));
    }
}
