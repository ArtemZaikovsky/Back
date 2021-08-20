package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.ContractorStatus;

public class ContractorStatusModelStubs {
    public static ContractorStatus getContractorStatus(Long id){
        return ContractorStatus.builder()
                .id(id)
                .name("Name " + id)
                .build();
    }
}
