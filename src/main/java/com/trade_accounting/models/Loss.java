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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loss")
public class Loss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;


    @ColumnDefault("false")
    private Boolean isSent = false;


    @ColumnDefault("false")
    private Boolean isPrint = false;


    private String comment;

    @OneToMany(fetch = FetchType.LAZY)
    private List<LossProduct> lossProducts;
}