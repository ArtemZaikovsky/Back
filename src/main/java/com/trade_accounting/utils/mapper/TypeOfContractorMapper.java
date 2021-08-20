package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeOfContractorMapper {
    //TypeOfContractor
    TypeOfContractorDto toDto(TypeOfContractor typeOfContractor);

    TypeOfContractor toModel(TypeOfContractorDto typeOfContractorDto);
}
