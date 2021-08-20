package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.TechnicalCard;

import java.util.List;

import static com.trade_accounting.services.impl.Stubs.model.TechnicalCardGroupModelStubs.getTechnicalCardGroup;
import static com.trade_accounting.services.impl.Stubs.model.TechnicalCardProductionModelStubs.getTechnicalCardProduction;

public class TechnicalCardModelStubs {
    public static TechnicalCard getTechnicalCard(Long id){
        return TechnicalCard.builder()
                .id(id)
                .name("Техническая карта " + id)
                .comment("Комментарий " + id)
                .productionCost("1000")
                .technicalCardGroup(getTechnicalCardGroup(1L))
                .finalProduction(List.of(
                        getTechnicalCardProduction(1L),
                        getTechnicalCardProduction(2L),
                        getTechnicalCardProduction(3L)
                ))
                .materials(List.of(
                        getTechnicalCardProduction(1L),
                        getTechnicalCardProduction(2L),
                        getTechnicalCardProduction(3L)
                        ))
                .build();
    }

}
