package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.BalanceAdjustmentDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.BalanceAdjustmentMapper;
import org.mapstruct.factory.Mappers;

public class BalanceAdjustmentDtoStubs {
    private static final BalanceAdjustmentMapper mapper = Mappers.getMapper(BalanceAdjustmentMapper.class);

    public static BalanceAdjustmentDto getBalanceAdjustmentDto(Long id) {
        return mapper.toDto(ModelStubs.getBalanceAdjustment(id));
    }
}
