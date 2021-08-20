package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "technical_operations")
public class TechnicalOperations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @ColumnDefault(value = "00001")
    private String number;

    @Column
    private LocalDate dateOperation;

    @OneToOne(fetch = FetchType.LAZY)
    private TechnicalCard technicalCard;

    @Column
    private Integer volume;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;
}
