package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.CorrectionProductDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.CorrectionProductMapper;
import org.mapstruct.factory.Mappers;

public class CorrectionProductDtoStubs {
    private static final CorrectionProductMapper mapper = Mappers.getMapper(CorrectionProductMapper.class);

    public static CorrectionProductDto getCorrectionProductDto(Long id) {
        return mapper.toDto(ModelStubs.getCorrectionProduct(id));
    }
}
