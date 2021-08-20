package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.MoneySubCashFlowDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.services.interfaces.MoneySubCashFlowService;
import com.trade_accounting.services.interfaces.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class MoneySubCashFlowServiceImpl implements MoneySubCashFlowService {

    private final PaymentService paymentService;

    public MoneySubCashFlowServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public List<MoneySubCashFlowDto> getAll() {
        List<MoneySubCashFlowDto> moneySubCashFlowDtoList = new ArrayList<>();
        BigDecimal cashComing;
        BigDecimal cashExpense;
        BigDecimal cashBalance;
        BigDecimal bankComing;
        BigDecimal bankExpense;
        BigDecimal bankBalance;
        BigDecimal finalCashComing;
        BigDecimal finalCashExpense;
        BigDecimal finalCashBalance;
        BigDecimal finalBankComing;
        BigDecimal finalBankExpense;
        BigDecimal finalBankBalance;
        Long count = 0L;

        //Создание начального остатка
        moneySubCashFlowDtoList.add(new MoneySubCashFlowDto(0L,"Начальный остаток",BigDecimal.ZERO,BigDecimal.ZERO
                ,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO));

        //Получение всех платежей
        List<PaymentDto> paymentDtos = paymentService.getAll();

        //Получение Листа с датами платежей
        List date = paymentDtos.stream().map(c -> c.getTime().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).distinct().sorted().collect(Collectors.toList());

        //Добавление Платежей внутри одной даты
        for (Object i:date) {
        //Наличные
            List<PaymentDto> cashComingPaymentDto = paymentDtos.stream()
                    .filter(t -> t.getTime().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).equals(i.toString()))
                    .filter(a -> a.getPaymentMethods().toString().equals("CASH"))
                    .filter(c -> c.getTypeOfPayment().toString().equals("INCOMING")).collect(Collectors.toList());
            if (cashComingPaymentDto.isEmpty()) {
                cashComing = BigDecimal.ZERO;
            } else {
                cashComing = cashComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            List<PaymentDto> cashExpensePaymentDto = paymentDtos.stream()
                    .filter(t -> t.getTime().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).equals(i.toString()))
                    .filter(a -> a.getPaymentMethods().toString().equals("CASH"))
                    .filter(c -> c.getTypeOfPayment().toString().equals("OUTGOING")).collect(Collectors.toList());
            if (cashExpensePaymentDto.isEmpty()) {
                cashExpense = BigDecimal.ZERO;
            } else {
                cashExpense = cashExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            cashBalance = cashComing.subtract(cashExpense);


            //Безнал
            List<PaymentDto> bankComingPaymentDto = paymentDtos.stream()
                    .filter(t -> t.getTime().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).equals(i.toString()))
                    .filter(a -> a.getPaymentMethods().toString().equals("BANK"))
                    .filter(c -> c.getTypeOfPayment().toString().equals("INCOMING")).collect(Collectors.toList());
            if (bankComingPaymentDto.isEmpty()) {
                bankComing = BigDecimal.ZERO;
            } else {
                bankComing = bankComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            List<PaymentDto> bankExpensePaymentDto = paymentDtos.stream()
                    .filter(t -> t.getTime().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).equals(i.toString()))
                    .filter(a -> a.getPaymentMethods().toString().equals("BANK"))
                    .filter(c -> c.getTypeOfPayment().toString().equals("OUTGOING")).collect(Collectors.toList());
            if (bankExpensePaymentDto.isEmpty()) {
                bankExpense = BigDecimal.ZERO;
            } else {
                bankExpense = bankExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            bankBalance = bankComing.subtract(bankExpense);

            BigDecimal allComing = cashComing.add(bankComing);
            BigDecimal allExpense = cashExpense.add(bankExpense);
            BigDecimal allBalance = cashBalance.add(bankBalance);

            MoneySubCashFlowDto moneySubCashFlowDto = new MoneySubCashFlowDto(count++,
                    i.toString(),bankComing, bankExpense, bankBalance,
                    cashComing, cashExpense, cashBalance, allComing, allExpense, allBalance);

            moneySubCashFlowDtoList.add(moneySubCashFlowDto);
        }

        //Получение конечного остатка
        //Наличные
        List<PaymentDto> cashComingPaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().toString().equals("CASH"))
                .filter(c -> c.getTypeOfPayment().toString().equals("INCOMING")).collect(Collectors.toList());
        if (cashComingPaymentDto.isEmpty()) {
            finalCashComing = BigDecimal.ZERO;
        } else {
            finalCashComing = cashComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        List<PaymentDto> cashExpensePaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().toString().equals("CASH"))
                .filter(c -> c.getTypeOfPayment().toString().equals("OUTGOING")).collect(Collectors.toList());
        if (cashExpensePaymentDto.isEmpty()) {
            finalCashExpense = BigDecimal.ZERO;
        } else {
            finalCashExpense = cashExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        finalCashBalance = finalCashComing.subtract(finalCashExpense);

        //Безнал
        List<PaymentDto> bankComingPaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().toString().equals("BANK"))
                .filter(c -> c.getTypeOfPayment().toString().equals("INCOMING")).collect(Collectors.toList());
        if (bankComingPaymentDto.isEmpty()) {
            finalBankComing = BigDecimal.ZERO;
        } else {
            finalBankComing = bankComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        List<PaymentDto> bankExpensePaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().toString().equals("BANK"))
                .filter(c -> c.getTypeOfPayment().toString().equals("OUTGOING")).collect(Collectors.toList());
        if (bankExpensePaymentDto.isEmpty()) {
            finalBankExpense = BigDecimal.ZERO;
        } else {
            finalBankExpense = bankExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        finalBankBalance = finalBankComing.subtract(finalBankExpense);

        BigDecimal finalAllComing = finalCashComing.add(finalBankComing);
        BigDecimal finalAllExpense = finalCashExpense.add(finalBankExpense);
        BigDecimal finalAllBalance = finalCashBalance.add(finalBankBalance);

        moneySubCashFlowDtoList.add(new MoneySubCashFlowDto(Long.MAX_VALUE,"Конечный остаток",finalBankComing,finalBankExpense,
                finalBankBalance,finalCashComing,finalCashExpense,finalCashBalance,finalAllComing,finalAllExpense,finalAllBalance));

        return moneySubCashFlowDtoList;
    }
}
