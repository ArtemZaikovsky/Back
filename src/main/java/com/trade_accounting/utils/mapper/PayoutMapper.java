package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Payout;
import com.trade_accounting.models.dto.PayoutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PayoutMapper {
    //Payout
    @Mappings({
            @Mapping(source = "retailStore.id", target = "retailStoreId"),
            @Mapping(source = "company.id", target = "companyId")
    })
    PayoutDto toDto(Payout payout);

    @Mappings({
            @Mapping(source = "retailStoreId", target = "retailStore.id"),
            @Mapping(source = "companyId", target = "company.id")
    })
    Payout toModel(PayoutDto payoutsDto);

}

