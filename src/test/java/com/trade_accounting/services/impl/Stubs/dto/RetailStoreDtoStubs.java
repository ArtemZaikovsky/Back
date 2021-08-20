package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.services.impl.Stubs.model.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.RetailStoreMapper;
import org.mapstruct.factory.Mappers;

public class RetailStoreDtoStubs {
    private static final RetailStoreMapper mapper = Mappers.getMapper(RetailStoreMapper.class);

    public static RetailStoreDto getDto(Long id) {
        return mapper.toDto(RetailStoreModelStubs.getRetailStore(id));
    }
}
