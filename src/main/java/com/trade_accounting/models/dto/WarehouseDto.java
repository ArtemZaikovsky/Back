package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {

    private Long id;

    private String name;

    private String sortNumber;

    private String address;

    private String commentToAddress;

    private String comment;

    public WarehouseDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
