package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierAccountDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long companyId;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long contractId;

    @NotNull
    private Long contractorId;

    private Boolean isSpend;

    private String comment;

}
