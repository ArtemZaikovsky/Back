package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.TypeOfPrice;

public class TypeOfPriceModelStubs {
    public static TypeOfPrice getTypeOfPrice(Long id) {
        return new TypeOfPrice(id, "test", "test");
    }
}
