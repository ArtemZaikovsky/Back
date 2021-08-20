package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.BalanceAdjustment;
import com.trade_accounting.models.dto.BalanceAdjustmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BalanceAdjustmentMapper {
    //    BalanceAdjustment
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
    })
    BalanceAdjustmentDto toDto(BalanceAdjustment balanceAdjustment);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
    })
    BalanceAdjustment toModel(BalanceAdjustmentDto balanceAdjustmentDto);

}
