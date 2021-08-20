package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.MovementDto;
import com.trade_accounting.services.impl.Stubs.model.MovementModelStubs;
import com.trade_accounting.utils.mapper.MovementMapper;
import org.mapstruct.factory.Mappers;

public class MovementDtoStubs {
    private static final MovementMapper mapper = Mappers.getMapper(MovementMapper.class);

    public static MovementDto getMovementDto(Long id) {
        return mapper.toDto(MovementModelStubs.getMovement(id));
    }
}


