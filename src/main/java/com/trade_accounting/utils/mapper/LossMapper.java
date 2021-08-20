package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Loss;
import com.trade_accounting.models.LossProduct;
import com.trade_accounting.models.dto.LossDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LossMapper {

    default Loss toModel(LossDto lossDto) {
        if (lossDto == null) {
            return null;
        }

        return Loss.builder()
                .id(lossDto.getId())
                .isSent(lossDto.getIsSent())
                .isPrint(lossDto.getIsPrint())
                .comment(lossDto.getComment())
                .build();
    }


    default LossDto toDto(Loss loss) {
        LossDto lossDto = new LossDto();
        if (loss == null) {
            return null;
        } else {
            lossDto.setId(loss.getId());
            lossDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(loss.getDate()));
            lossDto.setIsSent(loss.getIsSent());
            lossDto.setIsPrint(loss.getIsPrint());
            lossDto.setComment(loss.getComment());
            lossDto.setLossProductsIds(
                    loss.getLossProducts().stream()
                            .map(LossProduct::getId)
                            .collect(Collectors.toList())
            );

            if (loss.getCompany() == null) {
                return null;
            } else {
                lossDto.setCompanyId(loss.getCompany().getId());

                if (loss.getWarehouse() == null) {
                    return null;
                } else {
                    lossDto.setWarehouseId(loss.getWarehouse().getId());
                    return lossDto;
                }
            }
        }
    }
}
