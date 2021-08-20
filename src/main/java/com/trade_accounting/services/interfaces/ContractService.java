package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.ContractorDto;

import java.util.List;

public interface ContractService extends AbstractService<ContractDto>, SearchableService<Contract, ContractDto> {

    List<ContractDto> getAll(String searchContr);
}
