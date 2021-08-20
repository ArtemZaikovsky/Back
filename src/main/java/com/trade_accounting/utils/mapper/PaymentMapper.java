package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mappings({
            @Mapping(target = "company", ignore = true),
            @Mapping(target = "contractor", ignore = true),
            @Mapping(target = "contract", ignore = true),
            @Mapping(target = "project", ignore = true),
            @Mapping(target = "time", ignore = true)
    })
    Payment toModel(PaymentDto paymentDto);

    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "contract.id", target = "contractId"),
            @Mapping(source = "project.id", target = "projectId"),
            @Mapping(source = "time", target = "time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    PaymentDto toDto(Payment payment);
}
