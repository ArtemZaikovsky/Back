package com.trade_accounting.services.impl;

import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDto;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.interfaces.TaskCommentService;
import com.trade_accounting.utils.mapper.TaskCommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskCommentServiceImpl implements TaskCommentService {

    private final TaskCommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final CheckEntityServiceImpl checkEntityService;
    private final TaskCommentMapper taskCommentMapper;

    @Override
    public List<TaskCommentDto> search(Specification<TaskComment> specification) {
        return executeSearch(commentRepository, taskCommentMapper::toDto, specification);
    }

    @Override
    public List<TaskCommentDto> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(taskCommentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskCommentDto getById(Long id) {
        return commentRepository.findById(id)
                .map(taskCommentMapper::toDto).orElse(new TaskCommentDto());
    }

    @Override
    public TaskCommentDto create(TaskCommentDto dto) {
        checkEntityService.checkExistsTaskById(dto.getTaskId());
        var task = taskRepository.findById(dto.getTaskId()).get();

        var commentEntity = taskCommentMapper.toModel(dto);

        commentEntity.setPublisher(employeeRepository.getOne(dto.getPublisherId()));
        commentEntity.setTask(taskRepository.getOne(dto.getTaskId()));

        var saved = commentRepository.save(commentEntity);
        dto.setId(saved.getId());
        task.getTaskComments().add(commentEntity);

        return taskCommentMapper.toDto(saved);
    }

    public void createAll(List<TaskCommentDto> dtos) {
        dtos.forEach(dto -> {
            var entity = taskCommentMapper.toModel(dto);
            entity.setTask(taskRepository.getOne(dto.getTaskId()));
            entity.setPublisher(employeeRepository.getOne(dto.getPublisherId()));

            var taskOption = taskRepository.findById(dto.getTaskId());

            if (taskOption.isPresent()) {
                commentRepository.save(entity);
                taskOption.get().getTaskComments().add(entity);
            }
        });

    }

    @Override
    public TaskCommentDto update(TaskCommentDto dto) {
        return this.create(dto);
    }

    @Override
    public void deleteById(Long id) {
        checkEntityService.checkExistsTaskCommentById(id);
        commentRepository.findById(id).ifPresent(taskComment -> {
            var task = taskComment.getTask();
            if (task != null) {
                task.getTaskComments().remove(taskComment);
            }
            commentRepository.deleteById(id);
        });
    }
}

