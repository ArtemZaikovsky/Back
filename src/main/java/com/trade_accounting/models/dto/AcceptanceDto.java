package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptanceDto {

    private Long id;

    private String incomingNumber;

    private String comment;

    private String incomingNumberDate;

    private Long contractorId;

    private Long warehouseId;

    private Long contractId;

    Boolean isSent;

    Boolean isPrint;

    private List<AcceptanceProductionDto> acceptanceProduction;
}
