package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface TaskCommentService extends AbstractService<TaskCommentDto>,
        SearchableService<TaskComment, TaskCommentDto> {

    void createAll(List<TaskCommentDto> dtos);

}
