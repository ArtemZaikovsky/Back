package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.ContractorStatus;
import com.trade_accounting.models.dto.ContractorStatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractorStatusMapper {

    ContractorStatusDto toDto(ContractorStatus contractorStatus);

    ContractorStatus toModel(ContractorStatusDto status);

}
