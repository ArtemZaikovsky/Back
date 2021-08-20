package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.services.impl.Stubs.model.TechnicalCardModelStubs;
import com.trade_accounting.utils.mapper.TechnicalCardMapper;
import org.mapstruct.factory.Mappers;

public class TechnicalCardDtoStubs {
    private static final TechnicalCardMapper mapper = Mappers.getMapper(TechnicalCardMapper.class);

    public static TechnicalCardDto getDto(Long id) {
        return mapper.toDto(TechnicalCardModelStubs.getTechnicalCard(id));
    }
}
