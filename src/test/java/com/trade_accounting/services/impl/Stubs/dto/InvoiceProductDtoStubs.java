package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.services.impl.Stubs.model.InvoiceProductModelStubs;
import com.trade_accounting.utils.mapper.InvoiceProductMapper;
import org.mapstruct.factory.Mappers;

public class InvoiceProductDtoStubs {
    private static final InvoiceProductMapper mapper = Mappers.getMapper(InvoiceProductMapper.class);

    public static InvoiceProductDto getInvoiceProductDto(Long id) {
        return mapper.toDto(InvoiceProductModelStubs.getInvoiceProduct(id));
    }
}
