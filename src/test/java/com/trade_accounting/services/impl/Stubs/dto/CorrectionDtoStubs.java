package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.CorrectionDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.CorrectionMapper;
import org.mapstruct.factory.Mappers;

public class CorrectionDtoStubs {
    private static final CorrectionMapper mapper = Mappers.getMapper(CorrectionMapper.class);

    public static CorrectionDto getCorrectionDto(Long id) {
        return mapper.toDto(ModelStubs.getCorrection(id));
    }
}
