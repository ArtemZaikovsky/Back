package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.models.dto.TechnicalCardGroupDto;
import com.trade_accounting.services.impl.Stubs.model.TechnicalCardGroupModelStubs;
import com.trade_accounting.services.impl.Stubs.model.TechnicalCardModelStubs;
import com.trade_accounting.utils.mapper.TechnicalCardGroupMapper;
import com.trade_accounting.utils.mapper.TechnicalCardMapper;
import org.mapstruct.factory.Mappers;

public class TechnicalCardGroupDtoStubs {
    private static final TechnicalCardGroupMapper mapper = Mappers.getMapper(TechnicalCardGroupMapper.class);

    public static TechnicalCardGroupDto getDto(Long id) {
        return mapper.toDto(TechnicalCardGroupModelStubs.getTechnicalCardGroup(id));
    }
}
