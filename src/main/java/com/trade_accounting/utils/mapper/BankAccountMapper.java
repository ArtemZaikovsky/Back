package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.dto.BankAccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    //BankAccount
     BankAccountDto bankAccountToBankAccountDto(BankAccount bankAccount);

     BankAccount bankAccountDtoToBankAccount(BankAccountDto bankAccountDto);

     List<BankAccount> bankAccountDtoListToBankAccountList(List<BankAccountDto> bankAccountDtoList);

     List<BankAccountDto> bankAccountListToBankAccountDtoList(List<BankAccount> bankAccountList);
}
