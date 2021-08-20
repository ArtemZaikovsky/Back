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
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acceptances")
public class Acceptance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "incoming_number")
    private String incomingNumber;

    @Column(name = "comment")
    private String comment;

    @Column(name = "incoming_number_date", columnDefinition = "date default current_date")
    private LocalDate incomingNumberDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @Column(name = "is_sent")
    @ColumnDefault("false")
    Boolean isSent = false;

    @Column(name = "is_print")
    @ColumnDefault("false")
    Boolean isPrint = false;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AcceptanceProduction> acceptanceProduction;
}
