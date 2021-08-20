package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.ContractorDto;

import java.util.List;

public interface ContractorService extends AbstractService<ContractorDto>,
        SearchableService<Contractor, ContractorDto> {

    List<ContractorDto> getAll(String searchTerm);
}
