package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ReturnToSupplierDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.ReturnToSupplierMapper;
import org.mapstruct.factory.Mappers;

public class ReturnToSupplierDtoStubs {
    private static final ReturnToSupplierMapper mapper = Mappers.getMapper(ReturnToSupplierMapper.class);

    public static ReturnToSupplierDto getReturnToSupplierDto(Long id) {
        return mapper.toDto(ModelStubs.getReturnToSupplier(id));
    }

}
