package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TechnicalCardGroup;
import com.trade_accounting.models.dto.TechnicalCardGroupDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalCardGroupMapper {
    //TechnicalCardGroup
    TechnicalCardGroupDto toDto(TechnicalCardGroup technicalCardGroup);

    TechnicalCardGroup toModel(TechnicalCardGroupDto technicalCardGroupDto);
}
