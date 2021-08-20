package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.TypeOfContractorMapper;
import org.mapstruct.factory.Mappers;

public class TypeOfContractorDtoStubs {
    private static final TypeOfContractorMapper mapper = Mappers.getMapper(TypeOfContractorMapper.class);

    public static TypeOfContractorDto getTypeOfContractorDto(Long id) {
        return mapper.toDto(
                ModelStubs.getTypeOfContractor(id)
        );
    }
}
