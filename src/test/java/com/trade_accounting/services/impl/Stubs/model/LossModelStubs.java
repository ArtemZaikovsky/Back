package com.trade_accounting.services.impl.Stubs.model;


import com.trade_accounting.models.Loss;

import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getWarehouse;
import static com.trade_accounting.services.impl.Stubs.model.LossProductModelStubs.getLossProduct;

public class LossModelStubs {
    public static Loss getLoss(Long id) {
        return Loss.builder()
                .id(id)
                .lossProducts(List.of(
                        getLossProduct(1L),
                        getLossProduct(2L),
                        getLossProduct(3L)
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
