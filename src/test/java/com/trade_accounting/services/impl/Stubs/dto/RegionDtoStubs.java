package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.fias.RegionDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.RegionMapper;
import org.mapstruct.factory.Mappers;

public class RegionDtoStubs {
    private static final RegionMapper mapper = Mappers.getMapper(RegionMapper.class);

    public static RegionDto getRegionDto(Long id) {
        return mapper.toDto(ModelStubs.getRegion(id));
    }
}
