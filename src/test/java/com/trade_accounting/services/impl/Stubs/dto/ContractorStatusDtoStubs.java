package com.trade_accounting.services.impl.Stubs.dto;


import com.trade_accounting.models.dto.ContractorStatusDto;
import com.trade_accounting.services.impl.Stubs.model.ContractorStatusModelStubs;
import com.trade_accounting.utils.mapper.ContractorStatusMapper;
import org.mapstruct.factory.Mappers;

public class ContractorStatusDtoStubs {
    private static final ContractorStatusMapper mapper = Mappers.getMapper(ContractorStatusMapper.class);

    public static ContractorStatusDto getDto(Long id){
        return mapper.toDto(ContractorStatusModelStubs.getContractorStatus(id));
    }
}
