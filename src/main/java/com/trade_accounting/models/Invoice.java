package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Класс-модель накладной
 *
 * @param date         - дата составления накладной
 * @param typeOfInvoce - тип накладной
 * @param company      - наименование компании
 * @param contractor   - контрагент
 * @author Sanych
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data")
    private LocalDateTime date;

    @NotNull
    @Column(name = "type_of_invoice")
    private TypeOfInvoice typeOfInvoice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @Column(name = "is_Spend")
    @ColumnDefault("false")
    private Boolean isSpend;

    @Column(name = "comment")
    private String comment;
}
