package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Unit;
import com.trade_accounting.models.dto.UnitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    //Unit
    UnitDto toDto(Unit unit);

    Unit toModel(UnitDto unitDto);
}
