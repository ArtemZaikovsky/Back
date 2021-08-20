package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.dto.fias.CityDto;
import com.trade_accounting.models.fias.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mappings({
            @Mapping(source = "districtDto", target = "district"),
            @Mapping(source = "streetsDto", target = "streets")
    })
    City toModel(CityDto cityDto);

    @Mappings({
            @Mapping(source = "district", target = "districtDto"),
            @Mapping(source = "streets", target = "streetsDto")
    })
    CityDto toDto(City city);
}
