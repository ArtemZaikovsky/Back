package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Remain;

import static com.trade_accounting.services.impl.Stubs.model.UnitModelStubs.getUnit;

public class RemainModelStubs {
    public static Remain getRemain(Long id){
        return Remain.builder()
                .id(id)
                .name("name" + id)
                .vendorCode("Vendor Code" + id)
                .balance(1000)
                .irreducibleBalance(500)
                .reserve(100)
                .expectation(700)
                .available(650)
                .unit(getUnit(1L))
                .daysOnWarehouse(10)
                .costPrice(500)
                .sumOfCostPrice(500)
                .salesCost(300)
                .salesSum(300)
                .build();
    }

}
