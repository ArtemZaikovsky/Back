package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.PurchaseControl;

public class PurchaseControlModelStubs {
    public static PurchaseControl getPurchaseControl(Long id) {
        return PurchaseControl.builder()
                .id(id)
                .productName("test")
                .productCode(10000L)
                .articleNumber(1000L)
                .productMeasure("test")
                .productQuantity(100L)
                .historyOfSales(PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(1L))
                .currentBalance(PurchaseCurrentBalanceStubs.getPurchaseCurrentBalance(1L))
                .forecast(PurchaseForecastStubs.getPurchaseForecast(1L))
                .build();

    }
}
