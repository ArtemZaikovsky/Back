package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.MovementProduct;

import java.math.BigDecimal;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getProduct;

public class MovementProductModelStubs {
    public static MovementProduct getMovementProduct(Long id) {
        return new MovementProduct(
                id,
                getProduct(id),
                BigDecimal.ONE,
                BigDecimal.ONE
        );
    }
}
