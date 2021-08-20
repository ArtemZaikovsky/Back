package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.PaymentMapper;
import org.mapstruct.factory.Mappers;

public class PaymentDtoStubs {
    private static final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);
    public static PaymentDto getPaymentDto(Long id) {
        return mapper.toDto(ModelStubs.getPayment(id));
    }
}
