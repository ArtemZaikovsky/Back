package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Unit;

public class UnitModelStubs {
    public static Unit getUnit(Long id){
        return Unit.builder()
                .id(id)
                .fullName("Full Name")
                .shortName("Short Name")
                .sortNumber(id.toString())
                .build();
    }
}
