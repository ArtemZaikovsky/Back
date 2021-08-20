package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.PurchaseForecast;

public class PurchaseForecastStubs {
    public static PurchaseForecast getPurchaseForecast(Long id) {
        return PurchaseForecast.builder()
                .id(id)
                .reservedDays(7L)
                .reservedProducts(1L)
                .ordered(true)
                .build();
    }
}
