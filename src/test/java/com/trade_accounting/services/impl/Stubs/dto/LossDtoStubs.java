package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.LossDto;
import com.trade_accounting.services.impl.Stubs.model.LossModelStubs;
import com.trade_accounting.utils.mapper.LossMapper;
import org.mapstruct.factory.Mappers;

public class LossDtoStubs {
    private static final LossMapper mapper = Mappers.getMapper(LossMapper.class);

    public static LossDto getDto(Long id) {
        return mapper.toDto(LossModelStubs.getLoss(id));
    }
}


