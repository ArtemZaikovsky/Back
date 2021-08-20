package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Inventarization;
import com.trade_accounting.models.InventarizationProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.InventarizationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InventarizationMapper {
    //Inventarization
    default InventarizationDto toDto(Inventarization inventarization) {

        InventarizationDto inventarizationDto = new InventarizationDto();

        if (inventarization == null) {
            return null;
        } else {
            inventarizationDto.setId(inventarization.getId());
            inventarizationDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(inventarization.getDate()));
            inventarizationDto.setStatus(inventarization.getStatus());
            inventarizationDto.setComment(inventarization.getComment());

            Warehouse warehouse = inventarization.getWarehouse();
            if (warehouse == null) {
                return null;
            } else {
                inventarizationDto.setWarehouseId(warehouse.getId());

                Company company = inventarization.getCompany();
                if (company == null) {
                    return null;
                } else {
                    inventarizationDto.setCompanyId(company.getId());

                    List<Long> listIds = inventarization.getInventarizationProducts().stream()
                            .map(InventarizationProduct::getId)
                            .collect(Collectors.toList());
                    inventarizationDto.setInventarizationProductIds(listIds);

                    return inventarizationDto;
                }
            }
        }
    }

    @Mapping(target = "date", ignore = true)
    Inventarization toModel(InventarizationDto inventarizationDto);
}
