package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.BalanceAdjustment;
import com.trade_accounting.models.dto.BalanceAdjustmentDto;

import java.util.List;

public interface BalanceAdjustmentService extends AbstractService<BalanceAdjustmentDto>,
        SearchableService<BalanceAdjustment, BalanceAdjustmentDto> {

    List<BalanceAdjustmentDto> searchByString(String nameFilter);
}
