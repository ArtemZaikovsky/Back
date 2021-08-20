package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.TypeOfContractorDto;

public interface TypeOfContractorService extends AbstractService<TypeOfContractorDto> {

    TypeOfContractorDto getByName(String name);
}
