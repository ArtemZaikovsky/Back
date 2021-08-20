package com.trade_accounting.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_of_payment")
    @Enumerated(EnumType.STRING)
    private TypeOfPayment typeOfPayment;

    @Column(name = "payment_methods")
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethods;

    @Column(name = "number")
    @NotNull
    @ColumnDefault(value = "00001")
    private String number;

    @Column(name = "time")
    private LocalDateTime time;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @Column(name = "sum")
    private BigDecimal sum;

}
