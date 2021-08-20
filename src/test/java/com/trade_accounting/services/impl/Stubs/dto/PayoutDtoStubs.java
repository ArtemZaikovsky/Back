package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PayoutDto;
import com.trade_accounting.services.impl.Stubs.model.PayoutModelStubs;
import com.trade_accounting.utils.mapper.PayoutMapper;
import org.mapstruct.factory.Mappers;

public class PayoutDtoStubs {
    private static final PayoutMapper mapper = Mappers.getMapper(PayoutMapper.class);

    public static PayoutDto getDto(Long id) {
        return mapper.toDto(PayoutModelStubs.getPayout(id));
    }
}
