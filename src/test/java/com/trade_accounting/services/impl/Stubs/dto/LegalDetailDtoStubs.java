package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.LegalDetailMapper;
import org.mapstruct.factory.Mappers;

public class LegalDetailDtoStubs {
    private static final LegalDetailMapper mapper = Mappers.getMapper(LegalDetailMapper.class);

    public static LegalDetailDto getLegalDetailDto(Long id) {
        return mapper.toDto(ModelStubs.getLegalDetail(1L));
    }
}
