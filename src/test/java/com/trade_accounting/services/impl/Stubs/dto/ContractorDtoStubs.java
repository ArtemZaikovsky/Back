package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.ContractorMapper;
import org.mapstruct.factory.Mappers;

public class ContractorDtoStubs {
    private static final ContractorMapper mapper = Mappers.getMapper(ContractorMapper.class);

    public static ContractorDto getContractorDto(Long id) {
        return mapper.contractorToContractorDto(
                ModelStubs.getContractor(id)
        );
    }
}
