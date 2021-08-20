package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Movement;

import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getWarehouse;
import static com.trade_accounting.services.impl.Stubs.model.MovementProductModelStubs.getMovementProduct;

public class MovementModelStubs {
    public static Movement getMovement(Long id) {
        return new Movement(
                id, LocalDateTime.now(), getWarehouse(), getWarehouse(), getCompany(id),
                false, false,
                "Комментарий 1",
                List.of(getMovementProduct(1L),
                        getMovementProduct(2L),
                        getMovementProduct(3L))
        );
    }
}
