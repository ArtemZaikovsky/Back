package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.PayoutDto;

import java.util.List;

public interface PayoutService extends AbstractService<PayoutDto> {

    List<PayoutDto> getAllByParametrs(String searchTerm);
}