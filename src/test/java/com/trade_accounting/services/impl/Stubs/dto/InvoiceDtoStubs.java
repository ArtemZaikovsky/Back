package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.InvoiceMapper;
import org.mapstruct.factory.Mappers;

import static com.trade_accounting.services.impl.Stubs.model.InvoiceModelStubs.getInvoice;

public class InvoiceDtoStubs {
    private static final InvoiceMapper mapper = Mappers.getMapper(InvoiceMapper.class);

    public static InvoiceDto getInvoiceDto(Long id) {
        return mapper.toDto(
                getInvoice(id)
        );
    }
}
