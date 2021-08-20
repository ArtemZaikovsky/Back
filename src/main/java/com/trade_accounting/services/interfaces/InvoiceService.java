package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.dto.InvoiceDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InvoiceService extends AbstractService<InvoiceDto>, SearchableService<Invoice, InvoiceDto> {

    List<InvoiceDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice);

    @Transactional
    default List<InvoiceDto> getAll(String typeOfInvoice) {
        return search((root, query, builder)
                -> builder.equal(root.get("typeOfInvoice"), TypeOfInvoice.valueOf(typeOfInvoice)));
    }
}
