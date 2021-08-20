package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.ProjectMapper;
import org.mapstruct.factory.Mappers;

public class ProjectDtoStubs {
    private static final ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);
    public static ProjectDto getProjectDto(Long id) {
        return mapper.toDto(
                ModelStubs.getProject(id)
        );
    }
}
