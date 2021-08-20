package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;

import java.time.LocalDateTime;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;
//import static jdk.nashorn.internal.runtime.Debug.id;

public class InvoiceModelStubs {
    public static Invoice getInvoice(Long id) {
        return Invoice.builder()
                .id(id)
                .date(LocalDateTime.now())
                .typeOfInvoice(TypeOfInvoice.RECEIPT)
                .company(getCompany(id))
                .contractor(getContractor(id))
                .warehouse(getWarehouse(id))
                .isSpend(true)
                .comment("Комментарий")
                .build();
    }
}
