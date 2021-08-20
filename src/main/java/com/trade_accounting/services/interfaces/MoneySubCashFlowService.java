package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.MoneySubCashFlowDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoneySubCashFlowService {
    List<MoneySubCashFlowDto> getAll();

}
