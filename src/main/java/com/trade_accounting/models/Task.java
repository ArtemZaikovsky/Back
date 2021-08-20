package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Type(type = "text")
    private String description;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Employee taskEmployee;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Employee taskAuthor;

    @NotNull
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "deadline_date_time")
    private LocalDateTime deadlineDateTime;

    private boolean completed;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<TaskComment> taskComments;

}
