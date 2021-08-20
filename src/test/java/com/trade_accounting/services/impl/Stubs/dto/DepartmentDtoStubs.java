package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.DepartmentMapper;
import org.mapstruct.factory.Mappers;

public class DepartmentDtoStubs {
    private static final DepartmentMapper mapper = Mappers.getMapper(DepartmentMapper.class);
    public static DepartmentDto getDepartmentDto(Long id) {
        return mapper.toDto(
                ModelStubs.getDepartment(id));
    }
}
