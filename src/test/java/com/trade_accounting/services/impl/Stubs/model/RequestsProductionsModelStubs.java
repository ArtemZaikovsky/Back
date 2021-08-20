package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.RequestsProductions;

import java.time.LocalDate;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getTechnicalCard;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getWarehouse;

public class RequestsProductionsModelStubs {
    public static RequestsProductions getRequestsProductions(Long id) {
        return RequestsProductions.builder()
                .id(id)
                .numberOfTheCertificate("RP-001" + id)
                .dateOfTheCertificate(LocalDate.now())
                .technicalCard(getTechnicalCard(id))
                .volume(500)
                .warehouse(getWarehouse(id))
                .build();
    }
}

