package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {
    //TaskComment *Test
    @Mappings({
            @Mapping(source = "publisher.id", target = "publisherId"),
            @Mapping(source = "task.id", target = "taskId"),
    })
    TaskCommentDto toDto(TaskComment taskComment);

    @Mappings({
            @Mapping(source = "publisherId", target = "publisher.id"),
            @Mapping(source = "taskId", target = "task.id")
    })
    TaskComment toModel(TaskCommentDto taskCommentDto);
}
