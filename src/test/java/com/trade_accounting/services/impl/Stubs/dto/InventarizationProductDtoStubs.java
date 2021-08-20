package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.InventarizationProductDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.InventarizationProductMapper;
import org.mapstruct.factory.Mappers;

public class InventarizationProductDtoStubs {
    private static final InventarizationProductMapper mapper = Mappers.getMapper(InventarizationProductMapper.class);

    public static InventarizationProductDto getInventarizationProductDto(Long id) {
        return mapper.toDto(ModelStubs.getInventarizationProduct(id));
    }
}
