package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Production;
import com.trade_accounting.models.dto.ProductionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductionMapper {
    //Production

    @Mappings({
            @Mapping(source = "technicalCard.id", target = "technicalCardId"),
            @Mapping(source = "requestsProductions.id", target = "requestsProductionsId")
    })
    ProductionDto toDto(Production production);

    @Mappings({
            @Mapping(target = "technicalCard.id", ignore = true),
            @Mapping(target = "requestsProductions.id", ignore = true)
    })
    Production toModel(ProductionDto productionDto);
}
