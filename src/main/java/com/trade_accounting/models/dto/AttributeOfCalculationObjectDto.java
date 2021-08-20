package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeOfCalculationObjectDto {
    private Long id;
    private String name;
    private String sortNumber;
    private Boolean isService;

    public AttributeOfCalculationObjectDto(String name, String sortNumber, Boolean isService) {
        this.name = name;
        this.sortNumber = sortNumber;
        this.isService = isService;
    }
}
