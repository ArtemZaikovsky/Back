package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.RetailStore;

import java.math.BigDecimal;
import java.util.List;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getEmployee;

public class RetailStoreModelStubs {
    public static RetailStore getRetailStore(Long id) {
        return RetailStore.builder()
                .id(1L)
                .activityStatus("Был в сети вчера")
                .defaultTaxationSystem("ОСН")
                .isActive(true)
                .name("Ozon111")
                .orderTaxationSystem("УСН. Доход")
                .revenue(BigDecimal.valueOf(12000))
                .salesInvoicePrefix("SI")
                .company(getCompany(1L))
                .cashiers(List.of(getEmployee(id)))
                .build();
    }
}