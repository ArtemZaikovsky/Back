package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.MutualSettlements;
import com.trade_accounting.models.dto.MutualSettlementsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MutualSettlementsMapper {


    @Mappings({
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "employee.id", target = "employeeId")
    })
    MutualSettlementsDto toDto(MutualSettlements mutualSettlements);

    @Mappings({
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "employeeId", target = "employee.id")
    })
    MutualSettlements toModel(MutualSettlementsDto mutualSettlementsDto);
}
