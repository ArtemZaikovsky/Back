package com.trade_accounting.models.dto;

import com.trade_accounting.models.TypeOfInvoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDto {

    private Long id;
    private String comment;
    @NotNull
    private String date;
    @NotNull
    private String typeOfInvoice;
    @NotNull
    private Long companyId;
    @NotNull
    private Long contractorId;
    @NotNull
    private Long warehouseId;

    private Boolean isSpend;
    public InvoiceDto(Long id, String date,
                      String typeOfInvoice, Long companyId,
                      Long contractorId, Long warehouseId, boolean isSpend, String comment) {
        this.id = id;
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.companyId = companyId;
        this.contractorId = contractorId;
        this.warehouseId = warehouseId;
        this.isSpend = isSpend;
        this.comment = comment;
    }

    public InvoiceDto(Long id,
                      LocalDateTime date,
                      TypeOfInvoice typeOfInvoice,
                      Long companyId,
                      Long contractorId,
                      Long warehouseId,
                      Boolean isSpend,
                      String comment) {
        this.id = id;
        this.date = date.toString();
        this.typeOfInvoice = typeOfInvoice.toString();
        this.companyId = companyId;
        this.contractorId = contractorId;
        this.warehouseId = warehouseId;
        this.isSpend = isSpend;
        this.comment = comment;
    }
}
