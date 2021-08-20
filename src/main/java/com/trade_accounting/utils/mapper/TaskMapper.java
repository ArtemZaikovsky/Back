package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    //Task *Test
    @Mappings({
            @Mapping(source = "taskEmployee.id", target = "employeeId"),
            @Mapping(source = "taskAuthor.id", target = "taskAuthorId")
    })
    TaskDto taskToTaskDto(Task task);

    @Mappings({
            @Mapping(source = "employeeId", target = "taskEmployee.id"),
            @Mapping(source = "taskAuthorId", target = "taskAuthor.id"),
            @Mapping(target = "creationDateTime", ignore = true),
            @Mapping(target = "deadlineDateTime", ignore = true)
    })
    Task taskDtoToTask(TaskDto taskDto);
}
