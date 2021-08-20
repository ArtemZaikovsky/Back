package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.PositionMapper;
import org.mapstruct.factory.Mappers;

public class PositionDtoStubs {
    private static final PositionMapper mapper = Mappers.getMapper(PositionMapper.class);
    public static PositionDto getPositionDto(Long id) {
        return mapper.toDto(
                ModelStubs.getPosition(id));
    }
}
