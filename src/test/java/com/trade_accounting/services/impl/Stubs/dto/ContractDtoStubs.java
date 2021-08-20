package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.ContractMapper;
import org.mapstruct.factory.Mappers;

public class ContractDtoStubs {
    private static final ContractMapper mapper = Mappers.getMapper(ContractMapper.class);
    public static ContractDto getContractDto(Long id) {
        return mapper.toDto(ModelStubs.getContract(id));
    }
}
