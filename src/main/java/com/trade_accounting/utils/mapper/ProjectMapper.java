package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Project;
import com.trade_accounting.models.dto.ProjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    //Project
    ProjectDto toDto(Project project);

    Project toModel(ProjectDto projectDto);
}
