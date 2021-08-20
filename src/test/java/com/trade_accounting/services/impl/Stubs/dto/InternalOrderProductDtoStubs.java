package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.InternalOrderProductsDto;
import com.trade_accounting.services.impl.Stubs.model.InternalOrderProductModelStubs;
import com.trade_accounting.utils.mapper.InternalOrderProductMapper;
import org.mapstruct.factory.Mappers;

public class InternalOrderProductDtoStubs {
    private static final InternalOrderProductMapper mapper = Mappers.getMapper(InternalOrderProductMapper.class);

    public static InternalOrderProductsDto getDto(Long id) {
        return mapper.toDto(InternalOrderProductModelStubs.getInternalOrderProduct(id));
    }
}
