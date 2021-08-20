package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.LossProduct;
import com.trade_accounting.services.impl.Stubs.ModelStubs;

import java.math.BigDecimal;

public class LossProductModelStubs {
    public static LossProduct getLossProduct(Long id) {
        return LossProduct.builder()
                .id(id)
                .product(ModelStubs.getProduct(id))
                .amount(BigDecimal.ONE)
                .price(BigDecimal.ONE)
                .build();
    }
}
