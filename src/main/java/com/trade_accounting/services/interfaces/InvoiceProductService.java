package com.trade_accounting.services.interfaces;


import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.dto.InvoiceProductDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InvoiceProductService extends AbstractService<InvoiceProductDto>,
        SearchableService<InvoiceProduct, InvoiceProductDto> {

    @Transactional
    default List<InvoiceProductDto> searchByInvoiceId(Long id) {
        return search((root, query, builder) -> builder.equal(root.get("invoice").get("id"), id));
    }
}
