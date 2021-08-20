package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.services.impl.Stubs.model.InternalOrderModelStubs;
import com.trade_accounting.utils.mapper.InternalOrderMapper;
import org.mapstruct.factory.Mappers;

public class InternalOrderDtoStubs {
    private static final InternalOrderMapper mapper = Mappers.getMapper(InternalOrderMapper.class);

    public static InternalOrderDto getDto(Long id) {
        return mapper.toDto(InternalOrderModelStubs.getInternalOrder(id));
    }
}
