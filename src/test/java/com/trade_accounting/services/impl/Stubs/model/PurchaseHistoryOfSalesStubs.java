package com.trade_accounting.services.impl.Stubs.model;


import com.trade_accounting.models.PurchaseHistoryOfSales;

import java.math.BigDecimal;

public class PurchaseHistoryOfSalesStubs {
    public static PurchaseHistoryOfSales getPurchaseHistoryOfSales(Long id) {

        return PurchaseHistoryOfSales.builder()
                .id(1L)
                .sumOfProducts(new BigDecimal(5))
                .productPrice(ProductPriceModelStubs.getProductPrice(id))
                .productMargin(new BigDecimal(5))
                .productProfitMargin(new BigDecimal(5))
                .productSalesPerDay(3L)
                .build();
    }

}
