package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Position;
import com.trade_accounting.models.dto.PositionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    //Position
    PositionDto toDto(Position position);

    Position toModel(PositionDto position);
}
