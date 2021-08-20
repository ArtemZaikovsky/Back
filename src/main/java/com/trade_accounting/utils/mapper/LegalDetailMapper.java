package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.dto.LegalDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LegalDetailMapper {
    //LegalDetail
    @Mappings({
            @Mapping(source = "dateOfTheCertificate", target = "dateOfTheCertificate"),
            @Mapping(source = "typeOfContractor.id", target = "typeOfContractorDtoId"),
            @Mapping(source = "address.id", target = "addressDtoId")
    })
    LegalDetailDto toDto(LegalDetail legalDetail);

    @Mappings({
            @Mapping(target = "dateOfTheCertificate", ignore = true),
            @Mapping(source = "typeOfContractorDtoId", target = "typeOfContractor.id"),
            @Mapping(source = "addressDtoId", target = "address.id")
    })
    LegalDetail toModel(LegalDetailDto legalDetailDto);

}
