package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.AcceptanceProduction;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.AcceptanceProductionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AcceptanceProductionMapper {
    // AcceptanceProductionService
    default AcceptanceProductionDto toDto(AcceptanceProduction acceptanceProduction) {
        AcceptanceProductionDto acceptanceProductionDto = new AcceptanceProductionDto();
        if (acceptanceProduction == null) {
            return null;
        } else {
            acceptanceProductionDto.setId(acceptanceProduction.getId());
            acceptanceProductionDto.setAmount(acceptanceProduction.getAmount());

            Product product = acceptanceProduction.getProduct();
            if (product == null) {
                return null;
            } else {
                acceptanceProductionDto.setProductId(product.getId());
                return acceptanceProductionDto;
            }
        }
    }

    default AcceptanceProduction toModel(AcceptanceProductionDto acceptanceProductionDto) {
        AcceptanceProduction acceptanceProduction = new AcceptanceProduction();
        if (acceptanceProductionDto == null) {
            return null;
        }

        acceptanceProduction.setId(acceptanceProductionDto.getId());
        acceptanceProduction.setAmount(acceptanceProductionDto.getAmount());

        return acceptanceProduction;
    }
}
