package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.ContractorStatusDto;

import java.util.List;

public interface ContractorStatusService extends AbstractService<ContractorStatusDto> {
    List<ContractorStatusDto> getAll();
}
