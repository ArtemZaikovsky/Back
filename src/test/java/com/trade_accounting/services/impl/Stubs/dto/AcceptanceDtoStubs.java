package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.AcceptanceDto;
import com.trade_accounting.services.impl.Stubs.model.AcceptanceModelStubs;
import com.trade_accounting.utils.mapper.AcceptanceMapper;
import org.mapstruct.factory.Mappers;

public class AcceptanceDtoStubs {
    private static final AcceptanceMapper mapper = Mappers.getMapper(AcceptanceMapper.class);

    public static AcceptanceDto getAcceptanceDto(Long id) {
        return mapper.toDto(AcceptanceModelStubs.getAcceptance(id));
    }

}
