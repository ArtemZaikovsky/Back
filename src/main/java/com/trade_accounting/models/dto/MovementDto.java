package com.trade_accounting.models.dto;

import com.trade_accounting.models.CorrectionProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementDto {

    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long warehouseFromId;

    @NotNull
    private Long warehouseToId;

    @NotNull
    private Long companyId;

    private Boolean isSent = false;

    private Boolean isPrint = false;

    private String comment;

    private List<Long> movementProductsIds;
}
