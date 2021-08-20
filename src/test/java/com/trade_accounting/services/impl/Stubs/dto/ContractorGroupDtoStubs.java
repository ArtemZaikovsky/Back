package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.ContractorGroupMapper;
import org.mapstruct.factory.Mappers;

public class ContractorGroupDtoStubs {
    private static final ContractorGroupMapper mapper = Mappers.getMapper(ContractorGroupMapper.class);
    public static ContractorGroupDto getContractorGroupDto(Long id) {
        return mapper.toDto(
                ModelStubs.getContractorGroup(id)
        );
    }
}
