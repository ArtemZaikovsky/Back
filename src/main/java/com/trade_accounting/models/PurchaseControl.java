package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс-модель управление закупками
 *
 * @param id              - номер закупки
 * @param productName     - наименование товара
 * @param productCode     - код товара
 * @param articleNumber   - артикул
 * @param productMeasure  - еденицы измерения
 * @param productQuantity - число товаров
 * @param historyOfSales  -  история продаж
 * @param currentBalance  - текущий остаток
 * @param forecast        - число товаров
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchase_control")
public class PurchaseControl {
    @Id
    @NotNull
    private Long id;

    @NotNull
    private String productName;

    @NotNull
    private Long productCode;

    @NotNull
    private Long articleNumber;

    @NotNull
    private String productMeasure;

    private Long productQuantity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseHistoryOfSales historyOfSales;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseCurrentBalance currentBalance;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseForecast forecast;
}