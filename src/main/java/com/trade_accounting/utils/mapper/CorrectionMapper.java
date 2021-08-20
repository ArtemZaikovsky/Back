package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Correction;
import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.CorrectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CorrectionMapper {
    //    Correction
    default CorrectionDto toDto(Correction correction) {
        CorrectionDto correctionDto = new CorrectionDto();
        if (correction == null) {
            return null;
        } else {
            correctionDto.setId(correction.getId());
            correctionDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(correction.getDate()));
            correctionDto.setIsSent(correction.getIsSent());
            correctionDto.setIsPrint(correction.getIsPrint());
            correctionDto.setWriteOffProduct(correction.getWriteOffProduct());
            correctionDto.setComment(correction.getComment());

            Warehouse warehouse = correction.getWarehouse();
            if (warehouse == null) {
                return null;
            } else {
                correctionDto.setWarehouseId(warehouse.getId());

                Company company = correction.getCompany();
                if (company == null) {
                    return null;
                } else {
                    correctionDto.setCompanyId(company.getId());

                    List<Long> correctionProductIds = correction.getCorrectionProducts().stream()
                            .map(CorrectionProduct::getId)
                            .collect(Collectors.toList());
                    correctionDto.setCorrectionProductIds(correctionProductIds);
                    return correctionDto;
                }
            }
        }
    }

    @Mapping(target = "date", ignore = true)
    Correction toModel(CorrectionDto correctionDto);
}
