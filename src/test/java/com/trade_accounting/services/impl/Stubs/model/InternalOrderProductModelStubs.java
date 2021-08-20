package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.services.impl.Stubs.ModelStubs;

import java.math.BigDecimal;

public class InternalOrderProductModelStubs {
    public static InternalOrderProduct getInternalOrderProduct(Long id) {
        return InternalOrderProduct.builder()
                .id(id)
                .product(ModelStubs.getProduct(id))
                .amount(BigDecimal.ONE)
                .price(BigDecimal.ONE)
                .build();
    }
}
