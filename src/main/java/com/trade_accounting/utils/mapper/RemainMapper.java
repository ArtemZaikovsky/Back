package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Remain;
import com.trade_accounting.models.dto.RemainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RemainMapper {
    @Mappings({
            @Mapping(source = "unit.id", target = "unitId")
    })
    RemainDto toDto(Remain remain);

    @Mappings({
            @Mapping(source = "unitId", target = "unit.id")
    })
    Remain toModel(RemainDto remainDto);
}
