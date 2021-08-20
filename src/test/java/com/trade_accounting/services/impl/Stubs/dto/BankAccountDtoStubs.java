package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.BankAccountMapper;
import org.mapstruct.factory.Mappers;

public class BankAccountDtoStubs {
    private static final BankAccountMapper mapper = Mappers.getMapper(BankAccountMapper.class);
    public static BankAccountDto getBankAccountDto(Long id) {
        return mapper.bankAccountToBankAccountDto(
                ModelStubs.getBankAccount(id)
        );
    }
}
