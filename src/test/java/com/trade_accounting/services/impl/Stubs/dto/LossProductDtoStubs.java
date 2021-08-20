package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.LossProductDto;
import com.trade_accounting.services.impl.Stubs.model.LossProductModelStubs;
import com.trade_accounting.utils.mapper.LossProductMapper;
import org.mapstruct.factory.Mappers;

public class LossProductDtoStubs {
    private static final LossProductMapper mapper = Mappers.getMapper(LossProductMapper.class);

    public static LossProductDto getDto(Long id) {
        return mapper.toDto(LossProductModelStubs.getLossProduct(id));
    }
}
