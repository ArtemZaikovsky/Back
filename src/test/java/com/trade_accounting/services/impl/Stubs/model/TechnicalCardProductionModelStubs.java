package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.TechnicalCardProduction;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getProduct;

public class TechnicalCardProductionModelStubs {
    public static TechnicalCardProduction getTechnicalCardProduction(Long id){
        return TechnicalCardProduction.builder()
                .id(id)
                .amount(1L)
                .product(getProduct(1L))
                .build();

    }

}
