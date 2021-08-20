package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Movement;
import com.trade_accounting.models.MovementProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.MovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    //    Movement
    default MovementDto toDto(Movement movement) {
        MovementDto movementDto = new MovementDto();
        if (movement == null) {
            return null;
        } else {
            movementDto.setId(movement.getId());
            movementDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(movement.getDate()));
            movementDto.setIsSent(movement.getIsSent());
            movementDto.setIsPrint(movement.getIsPrint());
            movementDto.setComment(movement.getComment());

            Warehouse warehouseFrom = movement.getWarehouseFrom();
            Warehouse warehouseTo = movement.getWarehouseTo();
            if (warehouseFrom == null) {
                return null;
            } else {
                movementDto.setWarehouseFromId(warehouseFrom.getId());
                if (warehouseTo == null){
                    return null;
                } else {
                    movementDto.setWarehouseToId(warehouseTo.getId());

                    Company company = movement.getCompany();
                    if (company == null){
                        return null;
                    } else {
                        movementDto.setCompanyId(company.getId());

                        List<Long> movementProductIds = movement.getMovementProducts().stream()
                                .map(MovementProduct::getId)
                                .collect(Collectors.toList());

                        movementDto.setMovementProductsIds(movementProductIds);
                        return movementDto;
                    }
                }
            }
        }
    }

    @Mapping(target = "date", ignore = true)
    Movement toModel(MovementDto movementDto);
}
