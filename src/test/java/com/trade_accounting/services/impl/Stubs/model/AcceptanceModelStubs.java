package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.Project;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;

public class AcceptanceModelStubs {
    public static Acceptance getAcceptance(Long id) {
        return Acceptance.builder()
                .id(id)
                .acceptanceProduction(new ArrayList<>())
                .contract(getContract(1L))
                .contractor(getContractor(1L))
                .comment("Комментарий " + id)
                .incomingNumber("100")
                .incomingNumberDate(LocalDate.now())
                .warehouse(getWarehouse(1L))
                .build();
    }
}
