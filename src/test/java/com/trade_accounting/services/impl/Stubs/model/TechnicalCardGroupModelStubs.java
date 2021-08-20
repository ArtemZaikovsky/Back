package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.TechnicalCardGroup;

public class TechnicalCardGroupModelStubs {
    public static TechnicalCardGroup getTechnicalCardGroup(Long id){
        return TechnicalCardGroup.builder()
                .id(id)
                .name("Группа технических карт " + id)
                .comment("Комментарий " + id)
                .sortNumber(id.toString())
                .build();
    }
}
