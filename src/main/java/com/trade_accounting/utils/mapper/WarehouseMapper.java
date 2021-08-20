package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.WarehouseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    //Warehouse
    WarehouseDto toDto(Warehouse warehouse);

    Warehouse toModel(WarehouseDto warehouseDto);
}
