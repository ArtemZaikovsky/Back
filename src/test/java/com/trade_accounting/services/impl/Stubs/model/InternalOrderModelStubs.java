package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.InternalOrder;

import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getWarehouse;
import static com.trade_accounting.services.impl.Stubs.model.InternalOrderProductModelStubs.getInternalOrderProduct;

public class InternalOrderModelStubs {
    public static InternalOrder getInternalOrder(Long id) {
        return InternalOrder.builder()
                .id(id)
                .internalOrderProducts(List.of(
                        getInternalOrderProduct(1L),
                        getInternalOrderProduct(2L),
                        getInternalOrderProduct(3L)
                ))
                .date(LocalDateTime.now())
                .company(getCompany(1L))
                .warehouse(getWarehouse(1L))
                .isSent(false)
                .isPrint(true)
                .comment("Comment " + id)
                .build();
    }
}
