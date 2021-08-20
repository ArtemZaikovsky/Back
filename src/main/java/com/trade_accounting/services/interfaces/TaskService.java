package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDto;

import java.util.List;

public interface TaskService extends AbstractService<TaskDto>, SearchableService<Task, TaskDto> {

    void createAll(List<TaskDto> dtos);

}
