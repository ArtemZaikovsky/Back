package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.ProductPrice;

import java.math.BigDecimal;

public class ProductPriceModelStubs {
    public static ProductPrice getProductPrice(Long id) {
        return new ProductPrice(id, TypeOfPriceModelStubs.getTypeOfPrice(id), new BigDecimal(1));
    }
}
