package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PurchaseControlDto;
import com.trade_accounting.services.impl.Stubs.model.PurchaseControlModelStubs;
import com.trade_accounting.utils.mapper.PurchaseControlMapper;
import org.mapstruct.factory.Mappers;

public class PurchaseControlDtoStubs {
    private static final PurchaseControlMapper MAPPER = Mappers.getMapper(PurchaseControlMapper.class);

    public static PurchaseControlDto getDto(Long id) {
        return MAPPER.toDto(PurchaseControlModelStubs.getPurchaseControl(id));
    }
}
