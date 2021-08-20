package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String description;

    private Long employeeId;

    @NotNull
    private Long taskAuthorId;

    @NotNull
    private String creationDateTime;

    @NotNull
    private String deadlineDateTime;

    private boolean completed;

    private int commentCount;

    public TaskDto(Long id,
                   String description,
                   Long employeeId,
                   Long taskAuthorId,
                   LocalDateTime creationDateTime,
                   LocalDateTime deadlineDateTime,
                   boolean completed,
                   int commentCount) {
        this.id = id;
        this.description = description;
        this.employeeId = employeeId;
        this.taskAuthorId = taskAuthorId;
        this.creationDateTime = creationDateTime.toString();
        this.deadlineDateTime = deadlineDateTime.toString();
        this.completed = completed;
        this.commentCount = commentCount;
    }
}
