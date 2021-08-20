package com.trade_accounting.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс-модель возвраты поставщикам
 *
 * @param id         - номер поставщика
 * @param date       - время возврата
 * @param warehouse  - возврат со склада
 * @param company    - компания
 * @param contractor - контрагент
 * @param isSend     - отправлено
 * @param isPrint    - напечатано
 * @param comment    - комментарий
 * @param contract   - договор
 * @author Evstratov
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "return_suppliers")
public class ReturnToSupplier {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @ColumnDefault("false")
    private Boolean isSend;

    @ColumnDefault("false")
    private Boolean isPrint;

    @NotNull
    private String comment;
}
