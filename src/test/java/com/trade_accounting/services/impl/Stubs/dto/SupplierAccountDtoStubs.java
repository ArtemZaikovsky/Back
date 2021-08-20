package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.SupplierAccountMapper;
import org.mapstruct.factory.Mappers;

public class SupplierAccountDtoStubs {
    public static final SupplierAccountMapper mapper = Mappers.getMapper(SupplierAccountMapper.class);
    public static SupplierAccountDto getSupplierAccountDto(Long id) {
        return mapper.toDto(ModelStubs.getSupplierAccount(id));
    }
}
