package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.AcceptanceProduction;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getProduct;

public class AcceptanceProductionModelStubs {
    public static AcceptanceProduction getAcceptanceProduction(Long id) {
        return AcceptanceProduction.builder()
                .id(id)
                .product(getProduct(1L))
                .amount(100L)
                .build();
    }
}
